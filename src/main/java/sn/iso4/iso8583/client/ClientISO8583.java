/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.client;

import com.github.kpavlov.jreactive8583.client.ClientConfiguration;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import java.io.IOException;
import java.net.InetSocketAddress;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import sn.iso4.iso8583.listener.SignOnListener;
import sn.iso4.iso8583.type.ConnexionStatus;
import sn.iso4.iso8583.type.ConnexionType;
import sn.iso4.iso8583.type.IsoConfig;
import sn.iso4.iso8583.type.Session;
import sn.iso4.iso8583.utils.SessionList;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class ClientISO8583 {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        // - Create messageFactory
        MessageFactory<IsoMessage> messageFactory = ConfigParser.createFromClasspathConfig("j8583.xml");

        // - Create ClientConfiguration
        /*final ClientConfiguration configuration = ClientConfiguration.newBuilder()
                .withIdleTimeout(60)
                .withReconnectInterval(10)
                .withLogSensitiveData(true)
                .build();*/

        // - Create and init Iso8583Client
        Iso8583Client<IsoMessage> client = new Iso8583Client<>(new InetSocketAddress(IsoConfig.SERVER_IP, IsoConfig.SERVER_PORT), messageFactory);
        // - Add listeners
        client.addMessageListener(new SignOnListener(client));
        
        client.getConfiguration().replyOnError();
        client.init();
        client.connect();
        
        // - Connect and SignON
        if (client.isConnected()) {
            System.out.println("connecte...");
            SessionList.addSession(IsoConfig.SERVER_IP, new Session(IsoConfig.SERVER_IP, 80, IsoConfig.SERVER_IP, IsoConfig.SERVER_PORT, ConnexionStatus.SIGNOFF, ConnexionType.CLIENT2SERVER));
            
            // - Send first request
            IsoMessage msg = messageFactory.newMessage(0x1804);
            
            client.send(msg);
        } else {
            System.err.println("pas connecté...");
        }

        //client.shutdown();
    }
}

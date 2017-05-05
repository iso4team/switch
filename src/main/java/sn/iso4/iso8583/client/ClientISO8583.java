/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.client;

import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import java.io.IOException;
import java.net.InetSocketAddress;
import sn.iso4.iso8583.listener.SignOnListener;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class ClientISO8583 {

    public static void main(String[] args) throws IOException, InterruptedException {

        MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();
        Iso8583Client<IsoMessage> client = new Iso8583Client<>(new InetSocketAddress("10.13.0.2", 55755), messageFactory);
        client.addMessageListener(new SignOnListener());
        client.getConfiguration().replyOnError();
        client.init();
        client.connect();
        if (client.isConnected()) {
            System.out.println("connecte...");
            IsoMessage msg = messageFactory.newMessage(0x1804);
            client.send(msg);
        } else {
            System.err.println("pas connecté...");
        }
        //client.shutdown();
    }
}

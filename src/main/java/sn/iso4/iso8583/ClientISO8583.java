/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class ClientISO8583 {

    public static void main(String[] args) throws IOException, InterruptedException {

        MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();
        Iso8583Client<IsoMessage> client = new Iso8583Client<>(new InetSocketAddress("192.168.11.51", 1011), messageFactory);// [2]

        
        client.addMessageListener(new IsoMessageListener<IsoMessage>() { // [3]

            @Override
            public boolean applies(IsoMessage t) {
                System.out.println(t.getType());
                return t.getType() == 0x0810;
            }

            @Override
            public boolean onMessage(ChannelHandlerContext chc, IsoMessage t) {
                System.out.println("reponse server reçu...");
                return false;
            }

        });
        client.getConfiguration().replyOnError();// [4]
        client.init();// [5]
        System.out.println("after configs...");

        client.connect();// [6]
        System.out.println("before test connect...");
        if (client.isConnected()) { // [7]
            System.out.println("connecte...");
            IsoMessage msg = messageFactory.newMessage(0x1804);
            /*msg.setType(0x400);
             msg.setValue(3, "111111", IsoType.NUMERIC, 6);
             msg.setValue(11, "1455", IsoType.NUMERIC, 6);
             msg.setValue(12, "1455", IsoType.NUMERIC, 6);
             msg.setValue(13, "1455", IsoType.NUMERIC, 4);
             msg.setValue(102, "Test singe quoi", IsoType.LLVAR, 3);*/

            client.send(msg);// [8]
        } else {
            System.err.println("pas connecté...");
        }

        //client.shutdown();// [9]
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.server;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.server.Iso8583Server;
import com.github.kpavlov.jreactive8583.server.ServerConfiguration;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.impl.SimpleTraceGenerator;
import com.solab.iso8583.parse.ConfigParser;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class ServerISO8583 {

    public static void main(String[] args) throws IOException, InterruptedException {

        final MessageFactory<IsoMessage> messageFactory = ConfigParser.createFromClasspathConfig("g8583.xml");
        messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
        messageFactory.setUseBinaryMessages(false);
        messageFactory.setAssignDate(true);
        messageFactory.setTraceNumberGenerator(new SimpleTraceGenerator((int) (System.currentTimeMillis() % 1000000)));

         // - Create ClientConfiguration
        final ServerConfiguration configuration = ServerConfiguration.newBuilder()
                .withLogSensitiveData(true)
                .withEchoMessageListener(false)
                .build();
        
        final Iso8583Server<IsoMessage> server = new Iso8583Server<>(1011, configuration, messageFactory);

        server.addMessageListener(new IsoMessageListener<IsoMessage>() {

            @Override
            public boolean applies(IsoMessage t) {
                return t.getType() ==  0x1804;
            }

            @Override
            public boolean onMessage(ChannelHandlerContext chc, IsoMessage t) {
                System.out.println(String.format("message [%d] reçu", t.getType()));

                final IsoMessage msg = server.getIsoMessageFactory().newMessage(0x1814);
                msg.setField(24, IsoType.ALPHA.value("500", 3));
                msg.setField(39, IsoType.NUMERIC.value("000", 3));
                chc.writeAndFlush(msg);
                
                return false;
            }

        });

        server.init();
        server.start();
        if (server.isStarted()) {
            System.out.println("server demarre et en attente de connexions...");
        } else {
            System.err.println("Erreur demarrage server...");
        }
        //server.shutdown();// [8]
    }
}

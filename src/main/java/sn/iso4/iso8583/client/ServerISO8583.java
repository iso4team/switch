/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.client;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.server.Iso8583Server;
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
        
        final MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();
        messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
        messageFactory.setUseBinaryMessages(false);
        messageFactory.setAssignDate(true);
        messageFactory.setTraceNumberGenerator(new SimpleTraceGenerator((int) (System.currentTimeMillis() % 1000000)));

        final Iso8583Server<IsoMessage> server = new Iso8583Server<>(1011, messageFactory);

        server.addMessageListener(new IsoMessageListener<IsoMessage>() {

            @Override
            public boolean applies(IsoMessage t) {
                return true;
            }

            @Override
            public boolean onMessage(ChannelHandlerContext chc, IsoMessage t) {
                System.out.println("message reçu...");
                final IsoMessage response = server.getIsoMessageFactory().createResponse(t);
                response.setField(39, IsoType.ALPHA.value("800", 3));
                chc.writeAndFlush(response);
                return true;
            }

        });
        
        server.getConfiguration().setReplyOnError(true);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.listener;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.iso4.iso8583.type.ConnexionStatus;
import sn.iso4.iso8583.utils.SessionList;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class SignOnListener implements IsoMessageListener<IsoMessage> {

    private final Iso8583Client<IsoMessage> client;

    public SignOnListener(Iso8583Client<IsoMessage> client) {
        this.client = client;
    }

    @Override
    public boolean applies(IsoMessage isoMessage) {
        return isoMessage.getType() == 0x1814;
    }

    @Override
    public boolean onMessage(ChannelHandlerContext ctx, IsoMessage i) {
        System.out.println("reponse server reçu...");
        if (i.hasField(39)) {
            System.out.println("Field 39 [" + i.getField(39).getValue().toString() + "]");
            if ((i.getType() == 0x1814) && i.getField(39).getValue().toString().equals("800")) {
                System.out.println("[" + i.getType() + "|" + i.getField(39) + "] signON OK...");
                SessionList.updateSession("192.168.11.51", ConnexionStatus.SIGNON);
            }
        }
        // - send response
        ctx.writeAndFlush(client.getIsoMessageFactory().createResponse(i));
        
        System.out.println(SessionList.getSession("192.168.11.51").getConnexionStatus());
        return false;
    }

}

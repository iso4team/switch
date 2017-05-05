/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.listener;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class SignOnListener implements IsoMessageListener<IsoMessage> {

    @Override
    public boolean applies(IsoMessage isoMessage) {
        return (isoMessage.getType() == 0x0810) || (isoMessage.getType() == 0x1814) || (isoMessage.getType() == 0x0800);
    }

    @Override
    public boolean onMessage(ChannelHandlerContext ctx, IsoMessage i) {
        System.out.println("reponse server reçu...");
        if (i.hasField(39)) {
            System.out.println("Field 39 [" + i.getField(39).getValue().toString() + "]");
        }
        return true;
    }

}

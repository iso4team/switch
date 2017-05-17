/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.iso4.iso8583.listener;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 *
 * @author <ahmet.thiam@wari.com>
 */
public class EchoHandler extends ChannelInboundHandlerAdapter{

    private final MessageFactory isoMessageFactory;

    public EchoHandler(MessageFactory isoMessageFactory) {
        this.isoMessageFactory = isoMessageFactory;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.ALL_IDLE) {
                final IsoMessage echoMessage = createEchoMessage();
                ctx.write(echoMessage);
                ctx.flush();
            }
        }
    }

    private IsoMessage createEchoMessage() {
        return isoMessageFactory.newMessage(0x1804);
    }
}

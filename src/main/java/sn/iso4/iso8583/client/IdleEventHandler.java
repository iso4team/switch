package sn.iso4.iso8583.client;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class IdleEventHandler extends ChannelInboundHandlerAdapter {

    private final MessageFactory isoMessageFactory;

    public IdleEventHandler(MessageFactory isoMessageFactory) {
        this.isoMessageFactory = isoMessageFactory;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        /**/if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.ALL_IDLE) {
                final IsoMessage echoMessage = createEchoMessage();
                ctx.write(echoMessage);
                ctx.flush();
            }
        }
    }
    /**/
    private IsoMessage createEchoMessage() {
        return isoMessageFactory.newMessage(0x1804);
    }
}

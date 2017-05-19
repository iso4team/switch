
package sn.iso4.iso8583.listener;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;
import sn.iso4.iso8583.utils.SessionList;

/**
 *
 * @author Harouna Soumare <runkaou@gmail.com>
 */
public class TakListener implements IsoMessageListener<IsoMessage> {

    private final Iso8583Client<IsoMessage> client;

    public TakListener(Iso8583Client<IsoMessage> client) {
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
                // sauvegarde du tak 
                // si on envoi le premier echo test
                
            }
        }
        // - send response
        IsoMessage msg = client.getIsoMessageFactory().newMessage(0x1814);
        ctx.writeAndFlush(msg);
        
        System.out.println(SessionList.getSession("192.168.11.51").getConnexionStatus());
        return false;
    }

}

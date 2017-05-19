package sn.iso4.iso8583.requetes;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.impl.SimpleTraceGenerator;

import sn.iso4.iso8583.utils.Util;

/**
 *
 * @author Harouna Soumare<runkaou@gmail.com>
 */
public class BuildRequest {

    public static String isoHeader = "ISO70100";

    public static IsoMessage build1804Message(MessageFactory<IsoMessage> messageFactory, String codeFunction) {
        IsoMessage msg = messageFactory.newMessage(0x1804);
        try {

            msg.setField(7, IsoType.DATE10.value(Util.getNow2(), Util.getNow2().length()));
            msg.setField(11, IsoType.NUMERIC.value(145014, 6));
            msg.setField(12, IsoType.DATE12.value(Util.getNow("YYMMddHHmmss", 6)));
            msg.setField(24, IsoType.ALPHA.value(codeFunction, 3));
            msg.setField(25, IsoType.NUMERIC.value("", 4));
            msg.setField(33, IsoType.LLVAR.value("100580", 11)); // identification de l'organisme initiateur
            msg.setField(37, IsoType.NUMERIC.value(145014, 6));  // num reference recuovrement
            msg.setField(39, IsoType.NUMERIC.value("000", 3));
            msg.setField(128, IsoType.NUMERIC.value("314E1B", 6));

            byte[] data = msg.toString().getBytes();
            int len = data.length;

            //String NewHeader = Util.xLeftPad(4, '0', "" + len) + isoHeader;
            //msg.setIsoHeader(NewHeader);

            return msg;
        } catch (Throwable e) {
            return msg;
        }
    } // End 
}

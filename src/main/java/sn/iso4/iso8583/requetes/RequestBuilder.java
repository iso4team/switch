



/**
 *
 * @author Harouna Soumare<runkaou@gmail.com>
 */
public class RequestBuilder {

    public static String isoHeader = "ISO70100";

    /*public static IsoMessage buildSignOnRequestMessage(IsoMessage msg) {
        IsoMessage msg = init.mfact.newMessage(0x1804);
        try {

            msg.setValue(7, tbx.getNow2(), IsoType.DATE10, 0);
            msg.setValue(11, 789654, IsoType.NUMERIC, 6);
            msg.setValue(12, tbx.getNow(), IsoType.DATETIME, 0);
            msg.setValue(24, 801, IsoType.NUMERIC, 3);
            msg.setValue(25, 8010, IsoType.NUMERIC, 4);
            msg.setValue(33, 100580, IsoType.LLVAR, 11);
            msg.setValue(37, "834612295318", IsoType.NUMERIC, 12);
            msg.setValue(39, 000, IsoType.NUMERIC, 3);
            msg.setValue(128, "314E641B", IsoType.NUMERIC, 8);

            byte[] data = msg.toString().getBytes();
            int len = data.length;

            String NewHeader = tbx.xLeftPad(4, '0', "" + len) + isoHeader;
            msg.setIsoHeader(NewHeader);

            return msg;
        } catch (IOException e) {
            return msg;
        }
    }*/ 
}

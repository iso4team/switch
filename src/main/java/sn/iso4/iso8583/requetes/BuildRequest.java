package sn.iso4.iso8583.requetes;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;

import sn.iso4.iso8583.utils.Util;

/**
 *
 * @author Harouna Soumare<runkaou@gmail.com>
 */
public class BuildRequest {

	public static String isoHeader = "ISO70100";
	
	public static IsoMessage buildSignOnRequestMessage( MessageFactory<IsoMessage> messageFactory){
		IsoMessage msg = messageFactory.newMessage(0x1804);
		try {
			
		  msg.setField(7,  IsoType.DATE10.value(Util.getNow2(),Util.getNow2().length()));  
		  msg.setField(11, IsoType.NUMERIC.value(Util.generateStan(),6)); 
		  msg.setField(12, IsoType.DATE12.value(Util.getNow("YYMMddHHmmss",12))); 
		  msg.setField(24, IsoType.ALPHA.value("801", 3));
		  msg.setField(25, IsoType.NUMERIC.value("",4));  
		  msg.setField(33, IsoType.LLVAR.value("100580",11)); // identification de l'organisme initiateur
		  msg.setField(37, IsoType.NUMERIC.value(Util.generateStan(12),12));  // num reference recuovrement
		  //msg.setField(39, 000 , IsoType.NUMERIC,3);
		  msg.setField(128, IsoType.NUMERIC.value("314E641B",8)) ;
				  
		   byte[] data = msg.toString().getBytes();
		   int len = data.length;
		   
		   String NewHeader= Util.xLeftPad(4, '0', ""+len)+isoHeader;
		   msg.setIsoHeader(NewHeader);  
			  
		   return msg;
		   } catch (Throwable e) {
			    //logs.writeLog("--SID: Error in buildSignOnRequestMessage function ! Sending Process Fail !",0,true);
				return msg;	
		   }	   
	} // End 
}

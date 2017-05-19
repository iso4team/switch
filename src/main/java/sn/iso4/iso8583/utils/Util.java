/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.iso4.iso8583.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.solab.iso8583.impl.SimpleTraceGenerator;

/**
 * All utils
 *
 * @author <ahmet.thiam@wari.com>
 * @update Harouna SOUMARE <runkaou@gmail.com>
 */
public class Util {

    public static String SIGNON_CODE = "801";
    public static String ECHOTEST_CODE = "803";

    public static String GOOD_RESPONSE_1814 = "800";

    public static String getNow2() {
        String dateStr = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
            dateStr = dateFormat.format(new Date());
        } catch (Exception e) {

        }
        return dateStr;
    }

    public static String getNow(String Now, int CnxId) {
        String dateStr = "";
        try {
            //DateFormat dateFormat = new SimpleDateFormat ("ddMMyyyyHHmmss");
            DateFormat dateFormat = new SimpleDateFormat(Now);
            java.util.Date date = new java.util.Date();
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
        }
        return dateStr;
    }

    public static String generateStan(int... taille) {
        int len = taille.length > 0 ? 6 : taille[0];
        int ret = new SimpleTraceGenerator((int) (System.currentTimeMillis() % 10000)).nextTrace();
        System.out.println("sn.iso4.iso8583.utils.Util.generateStan()" + ret);
        return xLeftPad(len, '0', "" + ret);
    }

    public static String xLeftPad(int xlenght_must, char xch, String xstr) {
        StringBuilder xsb = new StringBuilder();
        int xlenght_now = xstr.length();
        if (xlenght_now < xlenght_must) {
            int xlenght_leave = xlenght_must - xlenght_now;
            for (int i = 0; i < xlenght_leave; i++) {
                xsb.append(xch);
            }
            xstr = xsb.toString() + xstr;
        }
        return xstr;
    }
}

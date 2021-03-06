package com.qick.phone.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
    
	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // get MessageDigest Object
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // update
            mdInst.update(btInput);
            // encrypt
            byte[] md = mdInst.digest();
            // transfer from byte array to char array
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }
    
}
package ling.yuze.utility;

import java.security.MessageDigest;

/**
 *
 * @author Roger
 */
public class Encryption {

    public static String toSHA256(String str) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes("UTF-8"));
            result = byte2Hex(md.digest());
        } catch (Exception e) {}
        
        return result;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                buffer.append("0");
            }
            buffer.append(temp);
        }

        return buffer.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainexamples;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.*;

/**
 *
 * @author karan.shah
 */
public class StringUtil {

    public static String applySha256(String dataToHash) {
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.print(ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import walletexample.StringUtil;

/**
 *
 * @author karan.shah
 */
public class Message {

    String data;
    PublicKey sender;
    PublicKey reciepient;
    private byte[] signature; //This is to prevent anybody else from spending funds in our wallet.

    public Message(PublicKey sender, PublicKey reciepient, String data) {
        this.sender = sender;
        this.reciepient = reciepient;
        //if i want to provide confidentiality so i need to encrypt data other wise ok
        //we are achiving data intigrity using digital signing the messages to verify proper sende
        this.data = Base64.encode(encrypt(data, reciepient));
    }

    public String getData(PrivateKey privateKey) {
        try {
            return decrypt(Base64.decode(data), privateKey);
        } catch (Exception e) {
            return null;
        }
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + this.data;
        signature = StringUtil.applyRSASign(privateKey, data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + this.data;
        return StringUtil.verifyRSA(data, signature, sender);
    }

    @Override
    public String toString() {
        return "{ Data : " + data + ", Sign: " + Base64.encode(signature) + "}";
    }

    private byte[] encrypt(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            System.out.println("cryptography.Message.encrypt()" + e.getMessage());
        }
        return null;
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));
        } catch (Exception e) {
            System.out.println("cryptography.Message.decrypt()");
        }
        return null;
    }
}

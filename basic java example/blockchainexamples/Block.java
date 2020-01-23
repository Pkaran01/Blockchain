/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainexamples;

import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;

/**
 *
 * @author karan.shah
 */
public class Block {

    private String previousHash;
    private String data;
    private String hash;
    private long timeStamp;
    private int nonce;
    final String secretKey = "ssshhhhhhhhhhh!!!!";

    public Block(String data, String previousHash) {
        this.data = AesEncryption.encrypt(data, secretKey);
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        // Mix the content of this block with previous hash to create the hash of this new block
        // and that's what makes it block chain
        this.hash = calculateBlockHash();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return AesEncryption.decrypt(data, secretKey);
    }

    public String getHash() {
        return hash;
    }

    private String calculateBlockHash() {
        return StringUtil.applySha256(previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + data);
    }

    /**
     *
     * @param prefix
     */
    public boolean mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return (hash.substring(0, prefix).equals(prefixString)) ? true : false;
    }

    @Override
    public String toString() {
        return "{ Data : " + data + ", Previous Hash: " + previousHash + ", Current Hash: " + hash + "}";
    }
}

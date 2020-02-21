/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.Security;
import java.util.ArrayList;

/**
 *
 * @author karan.shah
 */
public class Crypto {

    static ArrayList<Message> messagList = new ArrayList<>();

    public static void main(String arg[]) {
      
        User user1 = new User();
        User user2 = new User();
       
        //Start Transaction
        Message firstTransaction = new Message(user1.getPublicKey(), user2.getPublicKey(), "1>2");
        firstTransaction.generateSignature(user1.getPrivateKey());
        messagList.add(firstTransaction);
        
        messagList.forEach(System.out::println);
        
        System.out.println(firstTransaction.verifySignature());
        
        System.out.println(firstTransaction.getData(user2.getPrivateKey()));

       
    }
}

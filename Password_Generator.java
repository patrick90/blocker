/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author l
 */
public class Password_Generator {
    public static String generatePassword(int length){
        char[] chars = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()_+{}|<>?/\1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    
}

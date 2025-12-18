// 4.Write a java program to implement the DES algorithm logic? 

import java.util.Base64;
import javax.crypto.*;

public class p4_DESExample {

    private SecretKey generateKey(int size) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(size); // DES uses a 56-bit sub key size 
        return keyGenerator.generateKey();
    }

    private String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String... makeMeLearnDES) {
        p4_DESExample obj = new p4_DESExample();
        try {
            SecretKey secretKey = obj.generateKey(56); // DES uses a 56-bit sub key size 
            String plaintext = "Hello, All!";
            System.out.println("Original Text: " + plaintext);
            String encryptedText = obj.encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);
            String decryptedText = obj.decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

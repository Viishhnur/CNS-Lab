// 6.Write a java program to implement the Rijndael algorithm logic? 

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class p6_AESExample {
    
    // Method to generate a secret key 
    private SecretKey generateKey(int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }
    
    // Method to encrypt data using the AES algorithm 
    private String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    // Method to decrypt data using the AES algorithm 
    private String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String... helloAES) {
        p6_AESExample obj = new p6_AESExample(); 

        try {
            // Generate a secret key for AES 
            SecretKey secretKey = obj.generateKey(128);
            
            // Plain text to be encrypted 
            String plaintext = "Kash , hamara sapne purre hoga! __**__";
            System.out.println("Original Text: " + plaintext);
            
            // Encrypt the plain text 
            String encryptedText = obj.encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);
            
            // Decrypt the encrypted text 
            String decryptedText = obj.decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

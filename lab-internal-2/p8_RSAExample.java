// 8.Write a java program to implement RSA algorithm


import java.security.*;
import java.security.spec.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class p8_RSAExample {

    // Method to generate a key pair 
    private KeyPair generateKeyPair(int n) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(n);
        return keyPairGenerator.generateKeyPair();
    }

    // Method to encrypt data using RSA 
    private String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes =  cipher.doFinal(plaintext.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    // Method to decrypt data using RSA 
    private String decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }
    
    // Method to print the details of the RSA keys 
    private void printKeyDetails(PublicKey publicKey, PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey,RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey,RSAPrivateKeySpec.class);

        System.out.println("Public Key Modulus: " + publicKeySpec.getModulus());
        System.out.println("Public Key Exponent: " + publicKeySpec.getPublicExponent());
        System.out.println("Private Key Modulus: " + privateKeySpec.getModulus());
        System.out.println("Private Key Exponent: " + privateKeySpec.getPrivateExponent());
    }

    public static void main(String... helloRSA) {
        p8_RSAExample rsaExample = new p8_RSAExample(); 

        try {
            // Generate RSA key pair 
            KeyPair keyPair = rsaExample.generateKeyPair(2048);
            PublicKey publicKey = keyPair.getPublic(); // for encryption
            PrivateKey privateKey = keyPair.getPrivate(); // for decryption

            // Print the key details 
            rsaExample.printKeyDetails(publicKey, privateKey);


            // Text to be encrypted 
            String plaintext = "Hello, Viishhnu!";
            System.out.println("Original Text: " + plaintext);

            // Encrypt the text using the public key 
            String encryptedText = rsaExample.encrypt(plaintext, publicKey);
            System.out.println("Encrypted Text: " + encryptedText);


            // Decrypt the text using the private key 
            String decryptedText = rsaExample.decrypt(encryptedText, privateKey);
            System.out.println("Decrypted Text: " + decryptedText);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}

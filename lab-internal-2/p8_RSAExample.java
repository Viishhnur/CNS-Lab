// 8.Write a java program to implement RSA algorithm? 
// Program: 

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

public class p8_RSAExample {

    public static void main(String[] args) {
        try {
// Generate RSA key pair 
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Key size (2048 bits for strong security) 
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
// Print the key details 
            printKeyDetails(publicKey, privateKey);
// Text to be encrypted 
            String plaintext = "Hello, RSA!";
            System.out.println("Original Text: " + plaintext);
// Encrypt the text using the public key 
            byte[] encryptedText = encrypt(plaintext, publicKey);
            System.out.println("Encrypted Text: " + new String(encryptedText));
// Decrypt the text using the private key 
            String decryptedText = decrypt(encryptedText, privateKey);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// Method to encrypt data using RSA 

    public static byte[] encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext.getBytes());
    }
// Method to decrypt data using RSA 

    public static String decrypt(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }
// Method to print the details of the RSA keys 

    public static void printKeyDetails(PublicKey publicKey, PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey,
                RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey,
                RSAPrivateKeySpec.class);

        System.out.println("Public Key Modulus: " + publicKeySpec.getModulus());
        System.out.println("Public Key Exponent: " + publicKeySpec.getPublicExponent());
        System.out.println("Private Key Modulus: " + privateKeySpec.getModulus());
        System.out.println("Private Key Exponent: " + privateKeySpec.getPrivateExponent());
    }
}

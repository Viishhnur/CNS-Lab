// 9.Write a java program to calculate the message digest of text using the SHA-1 algorithm? 
// Program: 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p9_SHA1DigestExample {

    public static void main(String[] args) {
        String input = "Hello, World!";  // The input text for which SHA-1 hash is to be calculated 
        try {
// Create a MessageDigest instance for SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1");
// Update the MessageDigest with the bytes of the input string 
            md.update(input.getBytes());
// Perform the hash computation and get the resulting byte array 
            byte[] digest = md.digest();
// Convert the byte array into a hexadecimal string 
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
// Print the resulting SHA-1 hash 
            System.out.println("SHA-1 Digest: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-1 algorithm not found: " + e.getMessage());
        }
    }
}

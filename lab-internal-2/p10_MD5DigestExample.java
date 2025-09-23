// 10.Write a java program to calculate the message digest of text using the MD5 algorithm? 
// Program: 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p10_MD5DigestExample {

    public static void main(String[] args) {
        String input = "Hello, World!";  // The input text for which MD5 hash is to be calculated 
        try {
// Create a MessageDigest instance for MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");
// Update the MessageDigest with the bytes of the input string 
            md.update(input.getBytes());
// Perform the hash computation and get the resulting byte array 
            byte[] digest = md.digest();
// Convert the byte array into a hexadecimal string 
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
// Print the resulting MD5 hash 
            System.out.println("MD5 Digest: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not found: " + e.getMessage());
        }
    }
}

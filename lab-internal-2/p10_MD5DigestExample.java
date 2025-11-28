// 10.Write a java program to calculate the message digest of text using the MD5 algorithm? 
// Program: 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p10_MD5DigestExample {

    public String calculateMD5(String input) {
        try {
            // 1) Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2) Update the MessageDigest with the bytes of the input string
            md.update(input.getBytes());

            // 3) Perform the hash computation and get the resulting byte array
            byte[] digest = md.digest();

            // 4) Convert the byte array into a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static void main(String... learnMD5) {
        String input = "Hello, World!"; // The input text for which MD5 hash is to be calculated

        p10_MD5DigestExample obj = new p10_MD5DigestExample();
        String md5Hash = obj.calculateMD5(input);

        // Print the original text
        System.out.println("Original Text: " + input);
        // Print the resulting MD5 hash
        System.out.println("MD5 Digest: " + md5Hash);
    }
}

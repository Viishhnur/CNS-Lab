// 9.Write a java program to calculate the message digest of text using the SHA-1 algorithm

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p9_SHA1DigestExample {

    private String calculateSHA1(String input) {
        try {
            // 1) Create a MessageDigest instance for SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

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
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }

    public static void main(String... learnSHA1) {
        String input = "Hello, World!"; // The input text for which SHA-1 hash is to be calculated
        
        p9_SHA1DigestExample obj = new p9_SHA1DigestExample();

        // Print the resulting SHA-1 hash
        String sha1Hash = obj.calculateSHA1(input);
        // Print the original text
        System.out.println("Original Text: " + input);
        System.out.println("SHA-1 Digest: " + sha1Hash);
    }
}

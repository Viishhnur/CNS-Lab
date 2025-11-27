// 7.Write a java program the RC4 logic using cryptography; encrypt the text "Hello World" using 
// Blowfish. Create your own key using java key tool? 
// Program: 

import java.util.*;

public class p7_RC4 {

    private byte[] S = new byte[256];
    private int x = 0;
    private int y = 0;
    
    // Constructor to initialize the key
    public p7_RC4(byte[] key) {
        init(key);
    }
    
    // Initialize the permutation in the array S
    private void init(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;
            swap(i, j);
        }
    }
    
    // Swap elements in the array S
    private void swap(int i, int j) {
        byte temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }
    
    // Generate the key stream and perform encryption/decryption
    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ keyItem());
        }
        return ciphertext;
    }
    
    // Generate the next byte of the key stream
    private byte keyItem() {
        x = (x + 1) & 0xFF;
        y = (y + S[x]) & 0xFF;
        swap(x, y);
        return S[(S[x] + S[y]) & 0xFF];
    }

    public static void main(String... rc4Algo) {
        String plainText;
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Enter a key for RC4 encryption (e.g., mysecretkey):");
            plainText = sc.nextLine();
        }

        // Creating the RC4 object for encryption
        p7_RC4 rc4 = new p7_RC4(plainText.getBytes());
        byte[] ciphertext = rc4.encrypt(plainText.getBytes());

        // Decrypting the ciphertext
        p7_RC4 rc4Decrypt = new p7_RC4(plainText.getBytes()); // use new object to decrypt
        byte[] decryptedText = rc4Decrypt.encrypt(ciphertext); // RC4 is symmetric, so encryption and decryption are the
        // same

        System.out.println("Original Text: " + plainText);
        System.out.println("Encrypted Text: " + new String(ciphertext));
        System.out.println("Decrypted Text: " + new String(decryptedText));
    }
}

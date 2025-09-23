// 3. A)Write a java program to perform encryption and decryption using the Ceaser cipher 
// algorithm? 
// Program: 

import java.util.Scanner;

public class p3a_CaesarCipher {

    public static String encrypt(String message, int shift) {
    StringBuilder result = new StringBuilder();
    for (char ch : message.toCharArray()) {
        if (Character.isUpperCase(ch))
            ch = (char) ((ch - 'A' + shift) % 26 + 'A');
        else if (Character.isLowerCase(ch))
            ch = (char) ((ch - 'a' + shift) % 26 + 'a');
        result.append(ch);
    }
    return result.toString();
}

    public static String decrypt(String message, int shift) {
        return encrypt(message, 26 - shift); // Decrypt is reverse of encrypt with 26 - shift 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String message = scanner.nextLine();
        System.out.print("Enter the shift value (1-25): ");
        int shift = scanner.nextInt();
        scanner.close();
        if (shift < 1 || shift > 25) {
            System.out.println("Invalid shift value. Please enter a number between 1 and 25.");
            return;
        }
        String encryptedMessage = encrypt(message, shift);
        System.out.println("Encrypted Message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, shift);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

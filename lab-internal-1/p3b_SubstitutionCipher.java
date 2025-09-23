import java.util.*;

public class p3b_SubstitutionCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String message, String key) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        for (char ch : message.toCharArray()) {
            result.append(Character.isLetter(ch) ? key.charAt(ALPHABET.indexOf(ch)) : ch);
        }
        return result.toString();
    }

    public static String decrypt(String encryptedMessage, String key) {
        StringBuilder result = new StringBuilder();
        encryptedMessage = encryptedMessage.toUpperCase();
        for (char ch : encryptedMessage.toCharArray()) {
            result.append(Character.isLetter(ch) ? ALPHABET.charAt(key.indexOf(ch)) : ch);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = "QWERTYUIOPLKJHGFDSAZXCVBNM"; // Example substitution key
        System.out.println("Using substitution key: " + key);
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();
        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
        scanner.close();
    }
}

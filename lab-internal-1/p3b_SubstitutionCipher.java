import java.util.*;
import java.util.function.*;
public class p3b_SubstitutionCipher {

    private static final String ALPHABET,KEY; // we can initialize in static initalizer block

    static{
      ALPHABET  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
      KEY = "QWERTYUIOPLKJHGFDSAZXCVBNM"; // Example substitution key (This is a example of Private Key or Symmetric Key Cyrptography)
    }

    // takes two Strings and returns and String , so binary Operator can be used 
    private static final BinaryOperator<String> encrypt = (plainText,key) -> {

        // plainText to Cipher
        StringBuilder cipher = new StringBuilder();

        for (char ch : plainText.toUpperCase().toCharArray()) { // first convert to uppercase 
            cipher.append(Character.isLetter(ch) ? KEY.charAt(ALPHABET.indexOf(ch)) : ch);
        }
        return cipher.toString();
    };

    private static final BinaryOperator<String> decrypt = (cipher, key) -> {

        // Cipher to Plain Text
        StringBuilder plainText = new StringBuilder();
        for (char ch : cipher.toUpperCase().toCharArray()) {
            plainText.append(Character.isLetter(ch) ? ALPHABET.charAt(KEY.indexOf(ch)) : ch);
        }
        return plainText.toString();
    };

    public static void main(String[] args) {
        String message;
        try(Scanner sc = new Scanner(System.in)){

            System.out.print("Enter the message to encrypt: ");
            message = sc.nextLine();
        }
        
        System.out.println("Using substitution key: " + KEY);

        String encrypted = encrypt.apply(message, KEY);
        String decrypted = decrypt.apply(encrypted, KEY);
        
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }
}

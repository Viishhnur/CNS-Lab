// 3A) Write a java program to perform encryption and decryption using the Ceaser cipher algorithm

import java.util.Scanner;
import java.util.function.*;
public class p3a_CaesarCipher {

    // Predicates to find isUpper or isLower
    private static final Predicate<Character> isLower = ch -> Character.isLowerCase(ch);
    private static final Predicate<Character> isUpper = Character :: isUpperCase; // this is method reference

    private static final BiFunction<String,Integer,String> encrypt = (msg,shift) -> {
        // In ceaser cyper we should shift each character to next kth character(here k = shift)
        StringBuilder sb = new StringBuilder();

        for(char ch : msg.toCharArray()){
            if(isUpper.test(ch)){
                sb.append(((char)((ch - 'A' + shift) % 26 + 'A')));
            }else if(isLower.test(ch)){
                sb.append(((char)((ch - 'a' + shift) % 26 + 'a')));
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    };

    private static final BiFunction<String,Integer,String> decrypt = (msg,shift) -> {
        // In ceaser cyper we should shift each character to next kth character(here k = shift)

        // Approach-i) Traditional way
        // StringBuilder sb = new StringBuilder();

        // for(char ch : msg.toCharArray()){
        //     if(isUpper.test(ch)){
        //         sb.append(((char)((ch - 'A' - shift + 26) % 26 + 'A')));
        //     }else if(isLower.test(ch)){
        //         sb.append(((char)((ch - 'a' - shift + 26) % 26 + 'a')));
        //     }else{
        //         sb.append(ch); // for spaces or punctunations
        //     }
        // }
        // return sb.toString();

        // Approach-ii) Call encyrpt with shift = 26 - shift
        return encrypt.apply(msg, 26 - shift);
    };



    public static void main(String... caesarCipher) {
        String msg;
        int shift;

        try(Scanner sc = new Scanner(System.in)){
            System.out.print("Enter the message: ");
            msg = sc.nextLine();

            System.out.print("Enter the shift value (1-25): ");
            shift = sc.nextInt();
        }
        
        if (shift < 1 || shift > 25) {
            System.out.println("Invalid shift value. Please enter a number between 1 and 25.");
            return;
        }

        String encryptedMessage = encrypt.apply(msg, shift);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt.apply(encryptedMessage, shift);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

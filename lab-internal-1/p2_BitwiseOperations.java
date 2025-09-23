/* 2.Write a java program that contains a string (char pointer) with a value 'Hello World'. The 
program should AND , OR and XOR each character in this string with 127(01111111) and displays the result. 
*/

import java.util.function.*;
public class p2_BitwiseOperations{
    
    // takes two characters and returns a character
    private static final BinaryOperator<Character> calc = (ch,op) ->{
        return (char) switch (op) {
            case '&'-> (ch & 127);
            case '|'-> (ch | 127);
            case '^'-> (ch ^ 127);
            default -> ch;
        };
    };

    // A Bi-function which takes a string and operator 
    private static final BiFunction<String,Character,String> compute = (str,op) -> {
        StringBuilder sb = new StringBuilder();

        for(char ch : str.toCharArray()){
            sb.append(calc.apply(ch, op));
        }
        return sb.toString();
    };
    public static void main(String... bitwise) {
        
        String text = "Hello World";
        System.out.println("Original String: " + text);
        
        // Perform AND operation with 127 and display the result 
        System.out.println("AND with 127: " + compute.apply(text, '&'));
        
        // Perform OR operation with 127 and display the result 
        System.out.println("OR with 127: " + compute.apply(text, '|'));
        
        // Perform XOR operation with 127 and display the result 
        System.out.println("XOR with 127: " + compute.apply(text, '^'));
    }
}

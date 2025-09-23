/* 1.Write a Java program that contains a string (char pointer) with a value 'Hello World'. The 
program should XOR each character in this string with 0 and displays the result. 
 */
 

public class p1_XORWithZero {

    public static void main(String[] args) {
        String text = "Hello World";
        System.out.println("Original String: " + text);
        
        System.out.print("XOR with 0 result: ");

        for(char ch : text.toCharArray()){
            System.out.print((char)(ch ^ 0));
        }
    }
}

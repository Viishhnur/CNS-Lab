// 1.Write a Java program that contains a string (char pointer) with a value 'Hello World'. The 
// program should XOR each character in this string with 0 and displays the result. 
// Program: 

public class p1_XORWithZero {

    public static void main(String[] args) {
        String text = "Hello World";
        System.out.println("Original String: " + text);
        
        System.out.print("XOR with 0 result: ");
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char xorResult = (char) (c ^ 0);
            System.out.print(xorResult);
        }
        System.out.println();
    }
}

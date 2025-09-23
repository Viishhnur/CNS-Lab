// 3.C)Write a java program to perform encryption and decryption using the Hill cipher algorithm? 
// Program: 

import java.util.Scanner;

public class p3c_HillCipher {
// Function to perform matrix multiplication 

    public static int[] matrixMultiply(int[][] keyMatrix, int[] messageVector) {
        int[] result = new int[messageVector.length];
        for (int i = 0; i < keyMatrix.length; i++) {
            result[i] = 0;
            for (int j = 0; j < keyMatrix[i].length; j++) {
                result[i] += keyMatrix[i][j] * messageVector[j];
            }
            result[i] = result[i] % 26; // Perform modulo 26 operation 
        }
        return result;
    }
// Function to find the modular inverse of a number 

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }
// Function to calculate the inverse of a 2x2 matrix 

    public static int[][] inverseKeyMatrix(int[][] keyMatrix) {
        int determinant = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26;
        determinant = (determinant + 26) % 26;
        int inverseDeterminant = modInverse(determinant, 26);
        int[][] inverseMatrix = new int[2][2];
        inverseMatrix[0][0] = (keyMatrix[1][1] * inverseDeterminant) % 26;
        inverseMatrix[1][1] = (keyMatrix[0][0] * inverseDeterminant) % 26;
        inverseMatrix[0][1] = (-keyMatrix[0][1] * inverseDeterminant + 26) % 26;
        inverseMatrix[1][0] = (-keyMatrix[1][0] * inverseDeterminant + 26) % 26;
        return inverseMatrix;
    }
// Function to convert a string into an integer vector 

    public static int[] stringToVector(String text) {
        int[] vector = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            vector[i] = text.charAt(i) - 'A';
        }
        return vector;
    }
// Function to convert an integer vector into a string 

    public static String vectorToString(int[] vector) {
        StringBuilder text = new StringBuilder();
        for (int i : vector) {
            text.append((char) (i + 'A'));
        }
        return text.toString();
    }
// Function to encrypt the plaintext 

    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int[] messageVector = stringToVector(plaintext);
        int[] encryptedVector = matrixMultiply(keyMatrix, messageVector);
        return vectorToString(encryptedVector);
    }
// Function to decrypt the ciphertext 

    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int[][] inverseMatrix = inverseKeyMatrix(keyMatrix);
        int[] messageVector = stringToVector(ciphertext);
        int[] decryptedVector = matrixMultiply(inverseMatrix, messageVector);
        return vectorToString(decryptedVector);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// Input: 2x2 key matrix 
        int[][] keyMatrix = new int[2][2];
        System.out.println("Enter the 2x2 key matrix (values between 0 and 25):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }
// Input: plaintext (must be of length 2 for simplicity) 
        System.out.println("Enter the plaintext (length 2, uppercase letters only):");
        String plaintext = scanner.next().toUpperCase();
// Encrypt the plaintext 
        String ciphertext = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted Text: " + ciphertext);
// Decrypt the ciphertext 
        String decryptedText = decrypt(ciphertext, keyMatrix);
        System.out.println("Decrypted Text: " + decryptedText);
        scanner.close();
    }
}

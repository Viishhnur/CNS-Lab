// 3.C)Write a java program to perform encryption and decryption using the Hill cipher algorithm? 
// Program: 

import java.util.Scanner;

public class p3c_HillCipher {

    // Function to perform matrix multiplication
    private int[] matrixMultiply(int[][] keyMatrix, int[] messageVector,final int n) { // n x n * n x 1 = n x 1
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += keyMatrix[i][j] * messageVector[j];
            }
            result[i] = result[i] % 26; // Perform modulo 26 operation
        }
        return result;
    }

    // Function to find the modular inverse of a number 'a'
    // If a * x = 1 (mod m) , then x is the modular inverse of a
    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Function to calculate the inverse of a 2x2 matrix
    private int[][] inverseKeyMatrix(int[][] keyMatrix) {
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
    private int[] stringToVector(String text) {
        int[] vector = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            vector[i] = text.charAt(i) - 'A';
        }
        return vector;
    }

    // Function to convert an integer vector into a string
    private String vectorToString(int[] vector) {
        StringBuilder text = new StringBuilder();
        for (int i : vector) {
            text.append((char) (i + 'A'));
        }
        return text.toString();
    }

    private String encrypt(String plaintext, int[][] keyMatrix, final int n) {
        // first split into n sized blocks
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += n) {

            String sub = plaintext.substring(i, i + n);

            int[] messageVector = stringToVector(sub);

            int[] encryptedVector = matrixMultiply(keyMatrix, messageVector,n);
            cipherText.append(vectorToString(encryptedVector));
        }
        return cipherText.toString();
    }

    private String decrypt(String ciphertext, int[][] keyMatrix, final int n) {
        StringBuilder plainText = new StringBuilder();
        int[][] inverseMatrix = inverseKeyMatrix(keyMatrix); // calc inverse before u start decrypting
        for (int i = 0; i < ciphertext.length(); i += n) {

            String sub = ciphertext.substring(i, i + n);

            int[] messageVector = stringToVector(sub);

            int[] decryptedVector = matrixMultiply(inverseMatrix, messageVector,n);

            plainText.append(vectorToString(decryptedVector));
        }
        return plainText.toString();
    }

    public static void main(String... helloHillCipher) {
        int n;
        int[][] keyMatrix;
        String plaintext;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the value of n");
            n = sc.nextInt(); // ensure to input only n = 2 , since our logic works well for only n = 2
            keyMatrix = new int[n][n];

            System.out.println("Enter the " + n + "x" + n + " key matrix (values between 0 and 25):");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    keyMatrix[i][j] = sc.nextInt();
                }
            }

            // Input: plaintext (must be of multiples of n for simplicity)
            System.out.println("Enter the plaintext in multiples of n");
            plaintext = sc.next().toUpperCase();
        }

        p3c_HillCipher obj = new p3c_HillCipher();
        // Encrypt the plaintext
        String ciphertext = obj.encrypt(plaintext, keyMatrix, n);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = obj.decrypt(ciphertext, keyMatrix, n);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}

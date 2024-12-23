
package newpackage;

//hadeel alharthi 2210794

import java.io.*;
import java.util.*;

public class lab7 {

    public static void main(String[] args) {
        try {
            // Read the input file
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);

            // Read the number of items and capacity
            int n = scanner.nextInt();
            int capacity = scanner.nextInt();

            int[] weights = new int[n];
            int[] values = new int[n];

            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
                values[i] = scanner.nextInt();
            }
            scanner.close();

            // Solve the Knapsack problem
            int[][] dp = new int[n + 1][capacity + 1];

            // Fill the DP table
            for (int i = 1; i <= n; i++) {
                for (int w = 0; w <= capacity; w++) {
                    if (weights[i - 1] <= w) {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }

            // Backtrack to find the items included in the knapsack
            int[] solution = new int[n];
            int w = capacity;
            int totalWeight = 0;
            int totalValue = dp[n][capacity];

            for (int i = n; i > 0; i--) {
                if (dp[i][w] != dp[i - 1][w]) {
                    solution[i - 1] = 1; // Item is included
                    w -= weights[i - 1];
                    totalWeight += weights[i - 1];
                }
            }

            // Write the output to solution.txt
            FileWriter outputFile = new FileWriter("solution.txt");
            outputFile.write(totalWeight + " " + totalValue + "\n");

            for (int i = 0; i < n; i++) {
                outputFile.write(solution[i] + (i == n - 1 ? "" : " "));
            }

            outputFile.close();

            System.out.println("Knapsack solution written to solution.txt");

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found. Ensure 'input.txt' is in the correct directory.");
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}


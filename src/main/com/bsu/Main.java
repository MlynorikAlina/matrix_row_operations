package com.bsu;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of rows: ");
            int rowsNum = scanner.nextInt();
            if (rowsNum <= 0) {
                throw new InvalidParameterException("provided number is not natural");
            }

            System.out.println("Enter the number of columns: ");
            int columnsNum = scanner.nextInt();
            if (columnsNum <= 0) {
                throw new InvalidParameterException("provided number is not natural");
            }

            int[][] matrix = new int[rowsNum][columnsNum];
            System.out.println("Created matrix: ");
            printMatrix(matrix);

            boolean hasOrderedRows = false;

            for (int rowsCounter = 0; rowsCounter < rowsNum; rowsCounter++) {
                if (Math.abs(rowIsOrdered(matrix, rowsCounter)) == 1) {
                    hasOrderedRows = true;
                    System.out.println("Maximum in " + (rowsCounter + 1) + " row: " + Math.max(matrix[rowsCounter][0], matrix[rowsCounter][columnsNum - 1]));
                }
            }

            if (!hasOrderedRows) {
                System.out.println("There is no ordered rows in the matrix");
            }

        } catch (InvalidParameterException ex) {
            System.out.println("Error while reading: " + ex);
        } catch (Exception ex) {
            System.out.println("!!! Error while calculating: " + ex);
        }

    }

    private static int generateVariable() {
        int lowerBoundOfTheRange = 0;
        int higherBoundOfTheRange = 100;
        return lowerBoundOfTheRange + (int) (Math.random() * (double) higherBoundOfTheRange);
    }

    public static void printMatrix(int[][] matrix) {
        for (int rowsCounter = 0; rowsCounter < matrix.length; rowsCounter++) {
            for (int columnsCounter = 0; columnsCounter < matrix[0].length; columnsCounter++) {
                matrix[rowsCounter][columnsCounter] = generateVariable();
                System.out.printf("%2d ", matrix[rowsCounter][columnsCounter]);
            }
            System.out.print(System.lineSeparator());
        }
    }

    public static int rowIsOrdered(int[][] matrix, int numberOfTheRow) {
        boolean rowIsIncreasing = (matrix[numberOfTheRow][0] <= matrix[numberOfTheRow][matrix[0].length - 1]);

        for (int columnsCounter = 1; columnsCounter < matrix[0].length; ++columnsCounter) {
            if (rowIsIncreasing) {
                if (matrix[numberOfTheRow][columnsCounter - 1] > matrix[numberOfTheRow][columnsCounter])
                    return 0;
            } else if (matrix[numberOfTheRow][columnsCounter - 1] < matrix[numberOfTheRow][columnsCounter])
                return 0;
        }

        if (rowIsIncreasing) return 1;
        else return -1;
    }
}

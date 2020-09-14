package com.bsu;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int lowerBoundOfTheRange = 0;
            int higherBoundOfTheRange = 100;
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

            System.out.println("Created matrix: ");
            int[][] matrix = new int[rowsNum][columnsNum];

            for (int rowsCounter = 0; rowsCounter < rowsNum; rowsCounter++) {
                for (int columnsCounter = 0; columnsCounter < columnsNum; columnsCounter++) {
                    matrix[rowsCounter][columnsCounter] = generateVariable(lowerBoundOfTheRange, higherBoundOfTheRange);
                    System.out.printf("%2d ", matrix[rowsCounter][columnsCounter]);
                }
                System.out.print(System.lineSeparator());
            }

            boolean hasOrderedRows = false;

            for (int rowsCounter = 0; rowsCounter < rowsNum; rowsCounter++) {
                if (rowIsOrdered(matrix, rowsCounter, columnsNum)) {
                    hasOrderedRows = true;
                    System.out.println("Maximum in " + (rowsCounter + 1) + " row: " + Math.max(matrix[rowsCounter][0], matrix[rowsCounter][columnsNum - 1]));
                }
            }

            if (!hasOrderedRows) {
                System.out.println("There is no ordered rows in the matrix");
            }

        } catch (
                InvalidParameterException var11) {
            System.out.println("Error while reading: " + var11);
        } catch (
                Exception var12) {
            System.out.println("!!! Error while calculating: " + var12);
        }

    }

    private static int generateVariable(int lowerBoundOfTheRange, int higherBoundOfTheRange) {
        return lowerBoundOfTheRange + (int) (Math.random() * (double) higherBoundOfTheRange);
    }

    public static boolean rowIsOrdered(int[][] matrix, int numberOfTheRow, int columnsNum) {
        boolean rowIsOrdered = true;
        boolean rowIsIncreasing = (matrix[numberOfTheRow][0] <= matrix[numberOfTheRow][columnsNum - 1]);

        for (int columnsCounter = 1; columnsCounter < columnsNum && rowIsOrdered; ++columnsCounter) {
            if (rowIsIncreasing) {
                rowIsOrdered = (matrix[numberOfTheRow][columnsCounter - 1] <= matrix[numberOfTheRow][columnsCounter]);
            } else {
                rowIsOrdered = (matrix[numberOfTheRow][columnsCounter - 1] >= matrix[numberOfTheRow][columnsCounter]);
            }
        }

        return rowIsOrdered;
    }
}

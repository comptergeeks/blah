package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like your magic square to be like (?x?, enter one number)"); //prompt user for 2d array size
        int squareCount = sc.nextInt(); //get array size
        int[][] magicSquare = magicSquareGenerator(squareCount); //get array from generator
        int sum = squareCount * (squareCount * squareCount + 1) / 2; //get sum for each row/column/diagonal
        if (isMagicSquare(magicSquare, sum).equals("true")) { //state if the 2d array is magic
            System.out.println("The square is magic.");
        } else {
            System.out.println("The square is not magic");
        }
    }
    public static int[][] magicSquareGenerator(int squareCount) {
        int[][] twoDArray = new int[squareCount][squareCount];
        if (squareCount % 2 == 0) {
            twoDArray = evenMagicSquare(squareCount);
        } else {
            twoDArray = oddMagicSquareGenerator(squareCount);
        }
        return twoDArray;
    }
    // Function for building even number Magic square
    private static int[][] evenMagicSquare(int n)  {
        int[][] evenMagicSquare = new int[n][n];
        int i, j;
        // fill array with their index-counting
        // starting from 1
        for ( i = 0; i<n; i++)
        {
            for ( j = 0; j<n; j++)
                // filling array with its count value
                // starting from 1;
                evenMagicSquare[i][j] = (n*i) + j + 1;
        }
        // change value of Array elements
        // at fix location as per rule
        // (n*n+1)-arr[i][j]
        // Top Left corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 0; i<n/4; i++)
        {
            for ( j = 0; j<n/4; j++)
                evenMagicSquare[i][j] = (n*n + 1) - evenMagicSquare[i][j];
        }
        // Top Right corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 0; i< n/4; i++)
        {
            for ( j = 3* (n/4); j<n; j++)
                evenMagicSquare[i][j] = (n*n + 1) - evenMagicSquare[i][j];
        }
        // Bottom Left corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 3* n/4; i<n; i++)
        {
            for ( j = 0; j<n/4; j++)
                evenMagicSquare[i][j] = (n*n + 1) - evenMagicSquare[i][j];
        }
        // Bottom Right corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 3* n/4; i<n; i++)
        {
            for ( j = 3* n/4; j<n; j++)
                evenMagicSquare[i][j] = (n*n + 1) - evenMagicSquare[i][j];
        }
        // Centre of Matrix (order (n/2)*(n/2))
        for ( i = n/4; i<3* n/4; i++)
        {
            for ( j = n/4; j<3* n/4; j++)
                evenMagicSquare[i][j] = (n*n + 1) - evenMagicSquare[i][j];
        }
        // print magic square
        System.out.println("The Magic Square for " + n + ":");
        System.out.println("Sum of each row or column " + n * (n * n + 1) / 2 +
                ":");
        // Printing the magic-square
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) System.out.print(evenMagicSquare[i][j] + " ");
            System.out.println();
        }
        return evenMagicSquare;
    }
    //function to build odd number Magic Square
    private static int[][] oddMagicSquareGenerator(int n) {
        int[][] oddMagicSquare = new int[n][n];
        // Initialize position for 1
        int i = n / 2;
        int j = n - 1;
        // One by one put all values in magic square
        for (int num = 1; num <= n * n;) {
            if (i == -1 && j == n) // 3rd condition
            {
                j = n - 2;
                i = 0;
            }
            else {
                // 1st condition helper if next number
                // goes to out of square's right side
                if (j == n)
                    j = 0;
                // 1st condition helper if next number is
                // goes to out of square's upper side
                if (i < 0)
                    i = n - 1;
            }
            // 2nd condition
            if (oddMagicSquare[i][j] != 0) {
                j -= 2;
                i++;
                continue;
            }
            else
                // set number
                oddMagicSquare[i][j] = num++;
            // 1st condition
            j++;
            i--;
        }
        // print magic square
        System.out.println("The Magic Square for " + n
                + ":");
        System.out.println("Sum of each row or column "
                + n * (n * n + 1) / 2 + ":");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(oddMagicSquare[i][j] + " ");
            System.out.println();
        }
        return  oddMagicSquare;
    }
    public static String isMagicSquare(int[][] magicSquare, int sum) {
        int[] checkSums = new int[magicSquare.length*2 + 2];
        int counter = 0; //counter to make sure correct amount of sums are calculated
        int counter2 = 0; //counter to add elements in array
        for (int rows = 0; rows < magicSquare.length; rows++) {
            int sumOfRow = 0; //sum of row
            int sumOfColumn = 0; //sum of column
            for (int columns = 0; columns < magicSquare.length; columns++) {
                sumOfRow += magicSquare[rows][columns]; //add each element in the row to the sum
                sumOfColumn += magicSquare[columns][rows]; //add each element in the column to the sum
            }
            checkSums[counter2] = sumOfRow; //add the sum of the row into the array we will check later to make sure all the sums are correct
            counter2++; //move index up
            checkSums[counter2] = sumOfColumn; //add the sum of the column into the array
            counter2++; //move index up

        }
        int sumOfDiagonalLeft = 0; //left to right diagonal
        for (int i = 0; i < magicSquare.length; i++) { //get sum of left to right diagonal
            sumOfDiagonalLeft+= magicSquare[i][i];
        }
        checkSums[counter2] = sumOfDiagonalLeft;
        counter2++;
        int sumOfDiagonalRight = 0; //right to left diagonal
        for (int i = magicSquare.length; i > 0; i--) { //get sum of right to left diagonal
            sumOfDiagonalRight += magicSquare[i - 1 ][magicSquare.length - i];
        }
        checkSums[counter2] = sumOfDiagonalRight;
        counter2++;
        for (int i = 0; i < checkSums.length; i++) { //compare values in array to sum make sure each column, row, and diagonal has the right sum
            if(checkSums[i] == sum) {
                counter++;
            }
        }
        if (counter == magicSquare.length*2 + 2) { //return true or false
            System.out.println(Arrays.toString(checkSums));
            return "true";
        } else {
            System.out.println(Arrays.toString(checkSums));
            return "false";
        }

    }
}

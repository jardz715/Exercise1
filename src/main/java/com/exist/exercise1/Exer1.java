package com.exist.exercise1;

import java.util.Scanner;
import java.util.Random;

public class Exer1 {

	public static void main(String[] args) {
		int row, col;
		int choice = 0;
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter table row: ");	
		row = input.nextInt();
		System.out.println("Enter table col: ");	
		col = input.nextInt();

		// calls initRandMatrix
		String [][] mainArr = initRandMatrix(row, col);

		// calls print array
		printArr(mainArr);

		// Main Menu
		while(loop){
			try{
				System.out.println("\nMain Menu: ");
				System.out.println("[1] Search ");
				System.out.println("[2] Edit ");
				System.out.println("[3] Print ");
				System.out.println("[4] Reset ");
				System.out.println("[5] Exit ");
				System.out.println("Enter input: ");
				choice = input.nextInt();

				switch(choice){
					case 1: 
						System.out.println(" --Search-- ");
						String term; 
						System.out.println("Enter search term: ");
						term = input.next();
						searchFromMat(mainArr, term);
						break;
					case 2:
						System.out.println(" --Edit-- ");
						int repRow, repCol;
						String strToReplace;
						System.out.println("Enter table row: ");	
						repRow = input.nextInt();
						System.out.println("Enter table col: ");	
						repCol = input.nextInt();
						System.out.println("Enter replacement value: ");
						strToReplace = input.next();
						mainArr = replaceMatElem(mainArr, repRow, repCol, strToReplace); // calls replace method and updates mainArr
						break;
					case 3:
						System.out.println(" --Print-- ");
						// calls print array
						printArr(mainArr);
						break;
					case 4: 
						System.out.println(" --Reset-- ");
						int row2, col2;
						System.out.println("Enter table row: ");	
						row2 = input.nextInt();
						System.out.println("Enter table col: ");	
						col2 = input.nextInt();
						mainArr = initRandMatrix(row2, col2); // overwrite mainArr by calling initRandMatrix
						// calls print array
						printArr(mainArr);
						break;
					case 5: 
						System.out.println("Thank you for using the program!");
						System.exit(0);
						break;
					default:
						System.out.println("Invalid input.Please enter a numeric value.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a numeric value.");  // error handling to catch non-numeric input
				input.next();  // asks the user to enter choice agian to avoid infinite loop
			}
		}
	}

	public static void printArr(String [][] arr){
		for(int r = 0; r < arr.length; r++){
			for(int c = 0; c < arr[r].length; c++){
				System.out.print("| " + arr[r][c] + " |");
			}
			System.out.println("");
		}
	}

	public static String [][] initRandMatrix(int row, int col){
		Random rand = new Random();

		// main 2D array
		String [][] arr = new String[row][col];

		// array data
		String [] arrData = new String[(row * col)];  // 1D array to hold NxN values equvalent to the 2D matrix
		for (int i = 0; i < arrData.length; i++) {    // loops through 1D array
			String arrStr = "";                       // string to hold generated values as individual elements
			for (int j = 0; j < 3; j++) {             // loop to enable the program to generate 3-digits of elements individually
				arrStr += Character.toString((char) (rand.nextInt(122-65) + 65));	// compiles each digit into sets of 3 and store in the string variable
			}
			arrData[i] = arrStr;  // stores the generated string variable into the string array 
		}

		//populate array 
		int x = 0;                                    // counter to increment the 1D string array
		for(int r = 0; r < arr.length; r++){          // loop through 2D array
			for(int c = 0; c < arr[r].length; c++){
				arr[r][c] = arrData[x++];             // traverse 2D array while inserting elements of 1D array into it
			}
		}

		return arr;                                   // return populated 2D array
	}

	public static void searchFromMat(String [][] arr, String sTerm){
		int countOccurrence = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				// temp string to store matrix value retrived
				String temp = "";
				if (arr[i][j].contains(sTerm)) {
					System.out.println("String index: [" + i + "][" + j + "]"); // displays matrix index of found value
					temp += arr[i][j];                                          // assigns found value to temp variable
					countOccurrence += (temp.split(sTerm, -1).length - 1); // add total occurrence of search term for the whole matrix
					System.out.println("Occurrences for element in given index: " + (temp.split(sTerm, -1).length - 1)); // prints out occurence of search term in each element
				}
			}
		}
		System.out.println("Total Occurrence: " + countOccurrence);
	}

	public static String [][] replaceMatElem(String [][] arr, int row, int col, String strReplace){
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (i == row && j == col) {
					// array index located
					arr[i][j] = strReplace;
				}
			}
		}
		return arr;
	}

}

package com.steff.shelving.main;

import java.util.Scanner;

public class Main {

	private static final int DEFAULT_SHELF_WIDTH = 10;
	private static final int MIN_THICKNESS = 1;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int shelfWidth = DEFAULT_SHELF_WIDTH;
		int[] books = null;

		while (true) {
			System.out.println("\n--- BOOK SHELVING MENU ---");
			System.out.println("1. Input books thickness");
			System.out.println("2. Arrange on shelves");
			System.out.println("0. Exit");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				books = inputBooks(scanner, shelfWidth);
				break;

			case 2:
				if (books == null || books.length == 0) {
					System.out.println("Error! No books found!");
				} else {
					arrangeBooks(books, shelfWidth);
				}
				break;

			case 0:
				System.out.println("Exiting programme. Goodbye!");
				scanner.close();
				return;

			default:
				System.out.println("Invalid option! Please try again.");
				break;
			}
		}
	}

	public static int[] inputBooks(Scanner scanner, int shelfWidth) {
		System.out.print("Enter how many books you have: ");
		int count = scanner.nextInt();

		if (count <= 0) {
			System.out.println("Error! Count must be a positive number.");
			return new int[0];
		}

		int[] books = new int[count];
		for (int i = 0; i < count; i++) {
			int thickness;
			while (true) {
				System.out.print("Enter thickness for book #" + (i + 1) + ": ");
				thickness = scanner.nextInt();
				if (thickness >= MIN_THICKNESS && thickness <= shelfWidth) {
					break;
				}
				System.out.println("Error! Invalid thickness.");
			}
			books[i] = thickness;
		}

		System.out.println("All books added successfully!");
		return books;
	}

	public static void arrangeBooks(int[] books, int shelfWidth) {
		int shelfCount = 1;
		int currentShelfSum = 0;

		System.out.print("\nShelf " + shelfCount + ": [ ");

		for (int i = 0; i < books.length; i++) {
			int book = books[i];

			if (currentShelfSum + book <= shelfWidth) {
				if (currentShelfSum > 0) {
					System.out.print(", ");
				}
				currentShelfSum += book;
				System.out.print(book);
			} else {
				System.out.println(" ] - Total: " + currentShelfSum + " cm");

				shelfCount++;
				currentShelfSum = book;
				System.out.print("Shelf " + shelfCount + ": [ " + book);
			}
		}

		System.out.println(" ] - Total: " + currentShelfSum + " cm");

		System.out.println("---------------------------");
		System.out.println("Total shelves used: " + shelfCount);
	}

}

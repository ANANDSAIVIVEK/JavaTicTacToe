package Tictactoe;

import java.util.*;

public class TicTacToe {
	private static final int size = 3;
	private static final char empty = ' ';
	private static final char quit = 'q';

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			int xWins = 0;
			int oWins = 0;
			int draws = 0;

			while (true) {
				char winner = playGame(sc);

				if (winner == quit)
					break;
				if (winner == empty) {
					draws++;
					System.out.println("Match Drawn....");
				} else {
					if (winner == 'X')
						xWins++;
					else
						oWins++;
					System.out.println("Player " + winner + " wins!");
				}

				System.out.println("Score -> X: " + xWins);
				System.out.println("Score -> O: " + oWins);
				System.out.println("Drawn: " + draws);

				System.out.println("Wanna play Again(yes/No)");
				if (!readYesNo(sc)) {
					break;
				}

			}
			System.out.println("Thanks for Playing");
		}
	}

	
    /** Plays one full game. Returns 'X' or 'O' for a win, EMPTY for a draw, QUIT if input ended. */

	private static char playGame(Scanner sc) {
		char[][] board = new char[size][size];
		for (int row = 0; row < board.length; row++) {

			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = ' ';
			}
		}
		int moves = 0;
		char player = 'X';
		while (true) {
			printBoard(board);
			System.out.println("Player " + player + " enter:");
			int[] move = readMove(sc);
			if (move == null)
				return quit;

			int row = move[0];
			int col = move[1];

			if (!inBounds(row, col)) {
				System.out.println("Positions must be between 0 and " + (size - 1) + "Try Again");
				continue;
			}

			if (board[row][col] != empty) {
				System.out.println("Position already taken");
				continue;
			}

			board[row][col] = player;
			moves++;

			if (haveWon(board, player)) {
				printBoard(board);
				return player;
			}

			if (moves == size * size) {
				printBoard(board);
				return empty;
			}

			player = (player == 'X') ? 'O' : 'X';

		}
	}

	public static void printBoard(char[][] board) {
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(" " + board[row][col] + " |");
			}
			System.out.println();
			if (row < board.length - 1) {
				System.out.println("+---+---+---+");
			}
		}
		System.out.println();

	}

	public static boolean haveWon(char[][] board, char player) {
		// check every rows and column
		for (int i = 0; i < size; i++) {
			boolean rowWin = true;
			boolean colWin = true;
			for (int j = 0; j < size; j++) {
				rowWin &= board[i][j] == player;
				colWin &= board[i][j] == player;
			}
			if (rowWin || colWin) {
				return true;
			}
		}
		// check both diagonal
		boolean leftDiag = true;
		boolean rightDiag = true;
		for (int i = 0; i < board.length; i++) {
			leftDiag &= board[i][i] == player;
			rightDiag &= board[i][size - 1 - i] == player;
		}
		return leftDiag || rightDiag;
	}

	private static boolean inBounds(int row, int col) {
		return row >= 0 && row < size && col >= 0 && col < size;
	}

	/**
     * Reads a move as "row col" (or "row,col", or two separate lines).
     * Returns null when the input stream ends, so the game can exit cleanly.
     */
	private static int[] readMove(Scanner sc) {
		System.out.print("Enter row and column - (eg. 1 2): ");
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim().replace(",", " ").trim();

			if (line.isEmpty()) {
				System.out.print("Enter row and column - (eg. 1 2)");
				continue;
			}

			String[] parts = line.split("\\s");
			if (parts.length != 2) {
				System.out.print("please give exactly two numbers:");
				continue;
			}

			try {
				return new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) };
			} catch (NumberFormatException e) {
				System.out.println("numbers only, Try Again");
			}
		}
		return null;
	}

	private static boolean readYesNo(Scanner sc) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim().toLowerCase();
			if (line.startsWith("y")) {
				return true;
			}
			if (line.startsWith("n") || line.isEmpty()) {
				return false;
			}
			System.out.println("please Answer yes or no:");
		}
		return false;
	}

}

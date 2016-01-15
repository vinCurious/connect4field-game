
/* 
 * Connect4Field.java 
 * 
 * Version: Connect4Field.java,v 1.1 2015/09/20 21:05:46
 * 
 * Revisions: 
 *      Revision 1.1  2013/09/20 21:05:46
 *      Initial Version
 * 
 */

import java.util.Random;
import java.util.Scanner;

/**
 * This program implements Connect4Field game logic and Connect4FieldInterface.
 *
 * @author Vinay Vasant More
 *
 */
public class Connect4Field implements Connect4FieldInterface {

	static char[][] grid = new char[30][30];
	static int gridSizeCol;
	static int gridSizeRow;
	int count = 0;

	static int i, j;
	PlayerInterface thePlayers[] = new Player[2];

	Random rColumn = new Random();

	// Constructor
	public Connect4Field() {
		gridSizeCol = 25;
		gridSizeRow = 9;

		// assigning 1 values to whole character array
		for (i = 0; i < gridSizeRow; i++)
			for (j = 0; j < gridSizeCol; j++) {
				grid[i][j] = '1';
			}

		// assigning 0 values to actual grid for play
		for (i = 0; i < gridSizeRow; i++)
			for (j = i; j < gridSizeCol - i; j++) {
				grid[i][j] = '0';
			}
	}

	/**
	 * checkIfPiecedCanBeDroppedIn method checks whether the game piece can be
	 * dropped in specific column
	 * 
	 * @param column
	 *            Integer value denoting column number that user selected
	 *
	 * @return Method returns boolean True if the game piece can be dropped or
	 *         returns boolean False if the game piece can not be dropped.
	 *
	 */
	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		if (column < 0 || column > 24) {
			System.out.println("Enter column numbers between 0-24");
			return false;
		}

		if (grid[0][column] == '0')
			return true;
		else {
			System.out.println("Please enter other column number:");
			return false;
		}
	}

	/**
	 * dropPieces method adds game piece to selected column
	 * 
	 * @param column
	 *            Integer value denoting column number that user selected
	 *            gamePiece Game piece character specific to user
	 *
	 */
	@Override
	public void dropPieces(int column, char gamePiece) {
		if (checkIfPiecedCanBeDroppedIn(column)) {
			for (i = gridSizeRow - 1; i >= 0; i--) {
				if (grid[i][column] == '0') {
					grid[i][column] = gamePiece;
					break;
				}
			}
		}
	}

	/**
	 * didLastMoveWin method checks whether any user has won or not
	 *
	 * @return Method returns boolean True if user has won or returns boolean
	 *         False if user hasn't won
	 *
	 */
	@Override
	public boolean didLastMoveWin() {
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				if (grid[i][j] != '0' && grid[i][j] != '1') {
					// vertical check
					if (grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j] && grid[i][j] == grid[i + 3][j])
						return true;

					// horizontal check
					if (grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2] && grid[i][j] == grid[i][j + 3])
						return true;

					// diagonal down check
					if (grid[i][j] == grid[i + 1][j + 1] && grid[i][j] == grid[i + 2][j + 2]
							&& grid[i][j] == grid[i + 3][j + 3])
						return true;

					// diagonal up check
					if (i >= 3 && grid[i][j] == grid[i - 1][j + 1] && grid[i][j] == grid[i - 2][j + 2]
							&& grid[i][j] == grid[i - 3][j + 3])
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * isItaDraw method checks whether the game state has reached draw
	 *
	 * @return Method returns boolean True if the game state is draw or returns
	 *         boolean False if the game state hasn't reached draw
	 *
	 */
	@Override
	public boolean isItaDraw() {
		count = 0;
		if (didLastMoveWin())
			return false;

		for (j = 0; j < gridSizeCol; j++)
			if (grid[0][j] == '0')
				count = count + 1;

		if (count == 0)
			return true;
		else
			return false;
	}

	/**
	 * init method initializes player attributes before starting game
	 * 
	 * @param PlayerInterface
	 *            type playerA, playerB are initialized
	 *
	 */
	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		// TODO Auto-generated method stub
		String plA = playerA.getName();
		String plB = playerB.getName();
		char plAgamePiece = playerA.getGamePiece();
		char plBgamePiece = playerB.getGamePiece();
		System.out.println("Player1 plays with " + plAgamePiece + "\nPlayer2/Computer plays with " + plBgamePiece);
		System.out.println("Connect4Field game starts");
		thePlayers[0] = playerA;
		thePlayers[1] = playerB;
	}

	/**
	 * toString method prints the current Connect4Field grid
	 *
	 * @return Method returns single String capturing all grid values
	 *
	 */
	@Override
	public String toString() {
		String str = "";

		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				if (grid[i][j] != '1')
					str = str + grid[i][j] + " ";
				else
					str = str + "  ";
			}
			str = str + "\n";
		}
		return str;
	}

	/**
	 * playTheGame method allows two human players to play against each other
	 *
	 */
	@Override
	public void playTheGame() {
		int column;
		// System.out.println(this);
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					
					column = thePlayers[index].nextMove();

					if (checkIfPiecedCanBeDroppedIn(column))
						dropPieces(column, thePlayers[index].getGamePiece());
					else
						index = index - 1;
					System.out.println(this);
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[index].getName());
						break;
					}
				}
			}

		} while (!gameIsOver);
	}

	/**
	 * playTheGameComp method allows one human player to play against computer
	 *
	 */
	public void playTheGameComp() {
		// TODO Auto-generated method stub {
		int column;
		boolean gameIsOver = false;
		do {
			// player plays
			// System.out.println(this);
			if (isItaDraw()) {
				System.out.println("Draw");
				gameIsOver = true;
			} else {
				boolean flag = true;
				while (flag) {
					column = thePlayers[0].nextMove();
					if (checkIfPiecedCanBeDroppedIn(column)) {
						dropPieces(column, thePlayers[0].getGamePiece());
						flag = false;
					}
				}
				System.out.println(this);
				if (didLastMoveWin()) {
					gameIsOver = true;
					System.out.println("The winner is: " + thePlayers[0].getName());
					break;
				}
			}

			// computer plays
			if (isItaDraw()) {
				System.out.println("Draw");
				gameIsOver = true;
			} else {
				column = compAI(); // Computer AI call
				dropPieces(column, '*');
				System.out.println("Computer played * at " + column);
				System.out.println(this);
				if (didLastMoveWin()) {
					gameIsOver = true;
					System.out.println("The winner is Computer");
					break;
				}
			}

		} while (!gameIsOver);
	}

	/**
	 * compAI method determines a suitable column number for computer's turn by
	 * studying whole grid state.
	 *
	 * @return Method returns Integer column number so that computer can then
	 *         drop game piece at that position.
	 *
	 */
	public int compAI() {
		int aiSuggest = 0;

		char gamePiece = '*';

		// AI-1 Computer plays to win playing 4th piece
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				// diagonal down check
				if (i < 6 && j < 19) {
					if (grid[i][j] == '0' && grid[i + 1][j + 1] == '*' && grid[i + 1][j + 1] == grid[i + 2][j + 2]
							&& grid[i + 1][j + 1] == grid[i + 3][j + 3])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i + 2][j + 2] && grid[i + 1][j + 1] == '0'
							&& grid[i][j] == grid[i + 3][j + 3])
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i + 1][j + 1] && grid[i + 2][j + 2] == '0'
							&& grid[i][j] == grid[i + 3][j + 3])
						return j + 2;

					if (grid[i][j] == '*' && grid[i][j] == grid[i + 1][j + 1] && grid[i][j] == grid[i + 2][j + 2]
							&& grid[i + 3][j + 3] == '0')
						return j + 3;
				}
				// vertical check
				if (i < 6 && grid[i][j] == '0' && grid[i + 1][j] == '*' && grid[i + 1][j] == grid[i + 2][j]
						&& grid[i + 1][j] == grid[i + 3][j])
					return j;

				// horizontal check
				if (j < 22) {
					if (grid[i][j] == '0' && grid[i][j + 1] == '*' && grid[i][j + 1] == grid[i][j + 2]
							&& grid[i][j + 1] == grid[i][j + 3])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i][j + 2] && grid[i][j + 1] == '0'
							&& grid[i][j] == grid[i][j + 3])
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i][j + 1] && grid[i][j + 2] == '0'
							&& grid[i][j] == grid[i][j + 3])
						return j + 2;

					if (grid[i][j] == '*' && grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2]
							&& grid[i][j + 3] == '0')
						return j + 3;
				}

				// diagonal up check
				if (i > 2 && j > 2) {
					if (grid[i][j] == '0' && grid[i - 1][j + 1] == '*' && grid[i - 1][j + 1] == grid[i - 2][j + 2]
							&& grid[i - 1][j + 1] == grid[i - 3][j + 3])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i - 2][j + 2] && grid[i - 1][j + 1] == '0'
							&& grid[i][j] == grid[i - 3][j + 3])
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i - 1][j + 1] && grid[i - 2][j + 2] == '0'
							&& grid[i][j] == grid[i - 3][j + 3])
						return j + 2;

					if (grid[i][j] == '*' && grid[i][j] == grid[i - 1][j + 1] && grid[i][j] == grid[i - 2][j + 2]
							&& grid[i - 3][j + 3] == '0')
						return j + 3;
				}
			}
		}

		// AI-2 Computer plays to block other players 4th piece
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				// diagonal down check
				if (i < 6 && j < 19) {
					if (grid[i][j] == '0' && grid[i + 1][j + 1] == '+' && grid[i + 1][j + 1] == grid[i + 2][j + 2]
							&& grid[i + 1][j + 1] == grid[i + 3][j + 3])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i + 2][j + 2] && grid[i + 1][j + 1] == '0'
							&& grid[i][j] == grid[i + 3][j + 3])
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i + 1][j + 1] && grid[i + 2][j + 2] == '0'
							&& grid[i][j] == grid[i + 3][j + 3])
						return j + 2;

					if (grid[i][j] == '+' && grid[i][j] == grid[i + 1][j + 1] && grid[i][j] == grid[i + 2][j + 2]
							&& grid[i + 3][j + 3] == '0')
						return j + 3;
				}

				// vertical check
				if (i < 6 && grid[i][j] == '0' && grid[i + 1][j] == '+' && grid[i + 1][j] == grid[i + 2][j]
						&& grid[i + 1][j] == grid[i + 3][j])
					return j;

				// horizontal check
				if (j < 22) {
					if (grid[i][j] == '0' && grid[i][j + 1] == '+' && grid[i][j + 1] == grid[i][j + 2]
							&& grid[i][j + 1] == grid[i][j + 3])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i][j + 2] && grid[i][j + 1] == '0'
							&& grid[i][j] == grid[i][j + 3])
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i][j + 1] && grid[i][j + 2] == '0'
							&& grid[i][j] == grid[i][j + 3])
						return j + 2;

					if (grid[i][j] == '+' && grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2]
							&& grid[i][j + 3] == '0')
						return j + 3;
				}

				// diagonal up check
				if (i > 2 && j > 2) {
					if (grid[i][j] == '0' && grid[i - 1][j + 1] == '+' && grid[i - 1][j + 1] == grid[i - 2][j + 2]
							&& grid[i - 1][j + 1] == grid[i - 3][j + 3])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i - 2][j + 2] && grid[i - 1][j + 1] == '0'
							&& grid[i][j] == grid[i - 3][j + 3])
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i - 1][j + 1] && grid[i - 2][j + 2] == '0'
							&& grid[i][j] == grid[i - 3][j + 3])
						return j + 2;

					if (grid[i][j] == '+' && grid[i][j] == grid[i - 1][j + 1] && grid[i][j] == grid[i - 2][j + 2]
							&& grid[i - 3][j + 3] == '0')
						return j + 3;
				}

			}
		}

		// AI-3 Computer plays to block other players 3rd piece
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				// diagonal down check
				if (i < 7 && j < 21) {
					if (grid[i][j] == '0' && grid[i + 1][j + 1] == '+' && grid[i + 1][j + 1] == grid[i + 2][j + 2])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i + 2][j + 2] && grid[i + 1][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i + 1][j + 1] && grid[i + 2][j + 2] == '0')
						return j + 2;
				}

				// vertical check
				if (i < 7 && grid[i][j] == '0' && grid[i + 1][j] == '+' && grid[i + 1][j] == grid[i + 2][j])
					return j;

				// horizontal check
				if (j < 23) {
					if (grid[i][j] == '0' && grid[i][j + 1] == '+' && grid[i][j + 1] == grid[i][j + 2])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i][j + 2] && grid[i][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i][j + 1] && grid[i][j + 2] == '0')
						return j + 2;
				}

				// diagonal up check
				if (i >= 2 && j >= 2) {
					if (grid[i][j] == '0' && grid[i - 1][j + 1] == '+' && grid[i - 1][j + 1] == grid[i - 2][j + 2])
						return j;

					if (grid[i][j] == '+' && grid[i][j] == grid[i - 2][j + 2] && grid[i - 1][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '+' && grid[i][j] == grid[i - 1][j + 1] && grid[i - 2][j + 2] == '0')
						return j + 2;
				}
			}
		}

		// AI-4 Computer plays 3rd piece
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				// diagonal down check
				if (i < 7 && j < 21) {
					if (grid[i][j] == '0' && grid[i + 1][j + 1] == '*' && grid[i + 1][j + 1] == grid[i + 2][j + 2])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i + 2][j + 2] && grid[i + 1][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i + 1][j + 1] && grid[i + 2][j + 2] == '0')
						return j + 2;
				}

				// vertical check
				if (i < 7 && grid[i][j] == '0' && grid[i + 1][j] == '*' && grid[i + 1][j] == grid[i + 2][j])
					return j;

				// horizontal check
				if (j < 23) {
					if (grid[i][j] == '0' && grid[i][j + 1] == '*' && grid[i][j + 1] == grid[i][j + 2])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i][j + 2] && grid[i][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i][j + 1] && grid[i][j + 2] == '0')
						return j + 2;
				}

				// diagonal up check
				if (i >= 2 && j >= 2) {
					if (grid[i][j] == '0' && grid[i - 1][j + 1] == '*' && grid[i - 1][j + 1] == grid[i - 2][j + 2])
						return j;

					if (grid[i][j] == '*' && grid[i][j] == grid[i - 2][j + 2] && grid[i - 1][j + 1] == '0')
						return j + 1;

					if (grid[i][j] == '*' && grid[i][j] == grid[i - 1][j + 1] && grid[i - 2][j + 2] == '0')
						return j + 2;
				}
			}
		}

		// AI-5 computer plays 2nd piece
		for (i = 0; i < gridSizeRow; i++) {
			for (j = 0; j < gridSizeCol; j++) {
				// diagonal down check
				if (i < 8 && j < 22) {
					if (grid[i][j] == '0' && grid[i + 1][j + 1] == '*')
						return j;

					if (grid[i][j] == '*' && grid[i + 1][j + 1] == '0')
						return j + 1;
				}

				// vertical check
				if (i < 8 && grid[i][j] == '0' && grid[i + 1][j] == '*')
					return j;

				// horizontal check
				if (j < 24) {
					if (grid[i][j] == '0' && grid[i][j + 1] == '*')
						return j;

					if (grid[i][j] == '*' && grid[i][j + 1] == '0')
						return j + 1;
				}

				// diagonal up check
				if (i > 0 && j > 0) {
					if (grid[i][j] == '0' && grid[i - 1][j = 1] == '*')
						return j;

					if (grid[i][j] == '*' && grid[i - 1][j + 1] == '0')
						return j + 1;
				}
			}
		}
		boolean flagRan = true;

		// AI-6 computer plays random piece
		while (flagRan) {
			aiSuggest = rColumn.nextInt(24);
			if (checkIfPiecedCanBeDroppedIn(aiSuggest)) {
				flagRan = false;
				return aiSuggest;
			}
		}
		return aiSuggest;
	}

	/**
	 * The main program.
	 *
	 */
	public static void main(String[] args) {

	}
}

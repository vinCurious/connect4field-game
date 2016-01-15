
/* 
 * Player.java 
 * 
 * Version: Player.java,v 1.0 2015/09/20 21:05:46
 * 
 */

import java.util.*;

/**
 * This class defines player by implmenting PlayerInterface
 *
 * @author Vinay Vasant More
 *
 */
public class Player implements PlayerInterface {
	char gamePiece;
	String name;

	// Constructor
	public Player(Connect4FieldInterface theField, String playerName, char c) {
		// TODO Auto-generated constructor stub
		gamePiece = c;
		name = playerName;

	}

	/**
	 * getgamePiece method returns gamePiece of particular player
	 * 
	 * @return char - Returns gamePiece of the type char i.e. '+','*'.
	 *
	 */
	@Override
	public char getGamePiece() {
		// TODO Auto-generated method stub
		return gamePiece;
	}

	/**
	 * getName method returns Name of particular player
	 * 
	 * @return String - Returns player name string
	 *
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * nextMove method accepts column number from players where they want to
	 * drop game piece.
	 * 
	 * @return int - Returns column number integer where player wants to drop
	 *         game piece.
	 * 
	 */
	@Override
	public int nextMove() {
		// TODO Auto-generated method stub
		System.out.print(name + " " + gamePiece + ": Enter column number: ");
		Scanner sc = new Scanner(System.in);
		return (sc.nextInt());
	}
}

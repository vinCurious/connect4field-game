/* 
 * Connect4FieldInterface.java 
 * 
 * Version: Connect4FieldInterface.java,v 1.0 2015/09/20 21:05:46
 * 
 */

/**
 * Interface for Connect4Field class
 *
 * @author Vinay Vasant More
 *
 */
public interface Connect4FieldInterface {
	public boolean checkIfPiecedCanBeDroppedIn(int column);

	public void dropPieces(int column, char gamePiece);

	boolean didLastMoveWin();

	public boolean isItaDraw();

	public void init(PlayerInterface playerA, PlayerInterface playerB);

	public String toString();

	public void playTheGame();
}

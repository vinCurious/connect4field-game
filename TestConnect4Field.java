/* 
 * TestConnect4Field.java 
 * 
 * Version: TestConnect4Field.java,v 1.1 2015/09/20 21:05:46
 * 
 * Revisions: 
 *      Revision 1.1  2013/09/20 21:05:46
 *      Initial Version
 * 
 */

/**
 * This program tests Connect4Field game.
 *
 * @author Vinay Vasant More
 *
 */

public class TestConnect4Field {

	public Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
	public Player bPlayer = new Player(aConnect4Field, "B", '*');

	/**
	 * dropTest method checks whether game piece can be dropped in specific
	 * column
	 * 
	 * @param column
	 *            Integer which denotes column number
	 *
	 */
	public void dropTest(int column) {
		System.out
				.println("Can it be dropped in " + column + ": " + aConnect4Field.checkIfPiecedCanBeDroppedIn(column));
	}

	/**
	 * testIt method tests the implementation of Connect4Field game.
	 *
	 */
	public void testIt() {
		aConnect4Field = new Connect4Field();
		System.out.println(aConnect4Field);
		dropTest(-1);
		dropTest(0);
		dropTest(1);
		aConnect4Field.dropPieces(1, '+');
		System.out.println(aConnect4Field);
		aConnect4Field.dropPieces(1, '*');
		System.out.println(aConnect4Field);
		System.out.println(aConnect4Field.didLastMoveWin());
		System.out.println(aConnect4Field.isItaDraw());
		aConnect4Field.init(aPlayer, bPlayer);
		aConnect4Field.playTheGame(); // This function calls Player vs Player mode
		//aConnect4Field.playTheGameComp(); // This function calls Player vs Computer mode
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		new TestConnect4Field().testIt();
	}
}

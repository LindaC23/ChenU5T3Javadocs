/**
 * This class represents a player object
 *
 * @author Linda Chen
 * @author Steven Chen
 */
public class Player {
    /** The name of the player */
    private String name;
    /** The piece of the player */
    private String piece;
    /** The score of the player */
    private int score;

    /**
     * Instantiates a Player object
     * @param name The name of the player
     * @param piece The piece of the player
     */
    public Player(String name, String piece) {
        this.name = name;
        this.piece = piece;
        score = 0;
    }

    /**
     * Method that returns the name of the player
     *
     * @return The name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * Method that sets piece to the argument
     *
     * @param newPiece The new value for piece
     */
    public void setPiece(String newPiece) {
        piece = newPiece;
    }

    /**
     * Method that returns the piece of the player
     *
     * @return The piece of the player
     */
    public String getPiece(){
        return piece;
    }

    /**
     * Method that returns the score of the player
     *
     * @return The score of the player
     */
    public int getScore(){
        return score;
    }

    /**
     * Method that increments the player's score by 1
     */
    public void addScore(){
        score ++;
    }
}
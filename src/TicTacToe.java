import java.util.Scanner;
/**
 * This class represents a tic-tac-toe object
 *
 * @author Linda Chen
 * @author Steven Chen
 */
public class TicTacToe {
    // instance variables
    /** The first player of the tic-tac-toe game */
    private Player p1;
    /** The second player of the tic-tac-toe game */
    private Player p2;
    /** The current player of the tic-tac-toe game */
    private Player currentPlayer;
    // 2D array that contains the information for the tic tac toe game
    // we learned this from: https://www.geeksforgeeks.org/multidimensional-arrays-in-java/
    /** The information of the board of the tic-tac-toe game */
    private String[][] boardMoves = { {" ", " ", " "},
                                      {" ", " ", " "},
                                      {" ", " ", " "} };
    // constructors
    /**
     * Instantiates a TicTacToe object
     *
     * @param player1 The first player
     * @param player2 The second player
     */
    public TicTacToe(Player player1, Player player2){
        p1 = player1;
        p2 = player2;
        if ((int) (Math.random() * 2) + 1 == 1) {
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
            p1.setPiece("O");
            p2.setPiece("X");
        }
    }

    public TicTacToe(Player player1, Player player2, int firstMove) {
        p1 = player1;
        p2 = player2;
        if (firstMove == 1) {
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
        }
    }

    // setter and getter methods

    // other public methods
    // starts the tic tac toe game for a specific number of rounds
    public void play(int numRounds) {
        String temp = "";
        String coordinate = "";
        int row;
        int col;

        Scanner scan = new Scanner(System.in);
        for (int i = 1; i <= numRounds; i ++) {
            while (determineWinner() == 0){
                displayBoard();
                System.out.print("Where do you want to place your piece, " + currentPlayer.getName() + "? in (row, col) form: " );
                coordinate = scan.nextLine();
                while (!(coordinate.substring(0, 1).equals("(") && coordinate.substring(2, 3).equals(",") && coordinate.substring(3, 4).equals(" ") && coordinate.substring(5).equals(")"))){
                    System.out.print("You entered the incorrect format! Try again in (row, col) form with numbers between 1 and 3: ");
                    coordinate = scan.nextLine();
                }
                row = Integer.parseInt(coordinate.substring(coordinate.indexOf("(") + 1, coordinate.indexOf(","))) - 1;
                col = Integer.parseInt(coordinate.substring(coordinate.indexOf(", ") + 2, coordinate.indexOf(")"))) - 1;

                while(!boardMoves[row][col].equals(" ")) {
                    System.out.print("A piece has already been placed here! Enter another coordinate: ");
                    coordinate = scan.nextLine();
                    row = Integer.parseInt(coordinate.substring(coordinate.indexOf("(") + 1, coordinate.indexOf(","))) - 1;
                    col = Integer.parseInt(coordinate.substring(coordinate.indexOf(", ") + 2, coordinate.indexOf(")"))) - 1;
                }
                placePiece(row, col);
                switchPlayer();

            }
            if (determineWinner() != -1) {
                displayBoard();
                switchPlayer();
                currentPlayer.addScore();
                System.out.println(currentPlayer.getName() + " has won!");
                System.out.println(p1.getName() + " has a score of " + p1.getScore());
                System.out.println(p2.getName() + " has a score of " + p2.getScore());
                System.out.print("Type anything to continue: ");
                temp = scan.nextLine();
                clearBoard();
            } else {
                System.out.println("The game is tied!");
                System.out.println(p1.getName() + " has a score of " + p1.getScore());
                System.out.println(p2.getName() + " has a score of " + p2.getScore());
                System.out.print("Type anything to continue: ");
                temp = scan.nextLine();
                clearBoard();
            }
        }
        scan.close();
    }

    // starts the tic tac toe game (without a specified number of times to play)
    public void play() {
        boolean playAgain = true;
        String coordinate = "";
        int row;
        int col;
        String yesOrNo;
        Scanner scan = new Scanner(System.in);

        while (playAgain) {
            while (determineWinner() == 0){
                displayBoard();
                System.out.print("Where do you want to place your piece, " + currentPlayer.getName() + "? in (row, col) form: " );
                coordinate = scan.nextLine();
                while (!(coordinate.substring(0, 1).equals("(") && coordinate.substring(2, 3).equals(",") && coordinate.substring(3, 4).equals(" ") && coordinate.substring(5).equals(")"))){
                    System.out.print("You entered the incorrect format! Try again in (row, col) form with numbers between 1 and 3: ");
                    coordinate = scan.nextLine();
                }
                row = Integer.parseInt(coordinate.substring(coordinate.indexOf("(") + 1, coordinate.indexOf(","))) - 1;
                col = Integer.parseInt(coordinate.substring(coordinate.indexOf(", ") + 2, coordinate.indexOf(")"))) - 1;

                while(!boardMoves[row][col].equals(" ")) {
                    System.out.print("A piece has already been placed here! Enter another coordinate: ");
                    coordinate = scan.nextLine();
                    row = Integer.parseInt(coordinate.substring(coordinate.indexOf("(") + 1, coordinate.indexOf(","))) - 1;
                    col = Integer.parseInt(coordinate.substring(coordinate.indexOf(", ") + 2, coordinate.indexOf(")"))) - 1;
                }
                placePiece(row, col);
                switchPlayer();

            }
            if (determineWinner() != -1) {
                displayBoard();
                switchPlayer();
                currentPlayer.addScore();
                System.out.println(currentPlayer.getName() + " has won!");
                System.out.println(p1.getName() + " has a score of " + p1.getScore());
                System.out.println(p2.getName() + " has a score of " + p2.getScore());
                clearBoard();
                System.out.print("Would you like to play again? (y/n): ");
                yesOrNo = scan.nextLine();
                if (yesOrNo.equals("n")) {
                    playAgain = false;
                    System.out.println("Thanks for playing!");
                    System.out.println(p1.getName() + " has a final score of " + p1.getScore());
                    System.out.println(p2.getName() + " has a final score of " + p2.getScore());
                }
            } else {
                System.out.println("The game is tied!");
                System.out.println(p1.getName() + " has a score of " + p1.getScore());
                System.out.println(p2.getName() + " has a score of " + p2.getScore());
                clearBoard();
                System.out.print("Would you like to play again? (y/n): ");
                yesOrNo = scan.nextLine();
                if (yesOrNo.equals("n")) {
                    playAgain = false;
                    System.out.println("Thanks for playing!");
                    System.out.println(p1.getName() + " has a final score of " + p1.getScore());
                    System.out.println(p2.getName() + " has a final score of " + p2.getScore());
                }
            }
        }
        scan.close();
    }

    // private helper methods

    // displays the tic tac toe board
    private void displayBoard() {
        System.out.print("\033[H\033[2J");
        System.out.println(" | 1 | 2 | 3 |");
        System.out.println(" -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 3; j++){
                System.out.print("| " + boardMoves[i][j] + " ");

            }
            System.out.println("|");
            System.out.print("-|---|---|---|\n");
        }
    }

    // adds X or O into the row and column entered based on the currentPlayer's assigned piece and the
    private void placePiece(int row, int col){
        boardMoves[row][col] = currentPlayer.getPiece();
    }

    private void switchPlayer(){
        if (currentPlayer == p1){
            currentPlayer = p2;
        } else {
            currentPlayer = p1;
        }
    }

    // returns 1 if player 1 has won, 2 if player 2 has won, and 0 if neither
    private int determineWinner() {
        boolean tied = true;
        if (checkVerticalWinner() || checkHorizontalWinner() || checkDiagonalWinner()) {
            if (currentPlayer == p1) {
                return 1;
            }
            if (currentPlayer == p2) {
                return 1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardMoves[i][j].equals(" ")) {
                    tied = false;
                }
            }
        }
        if (tied) {
            return -1;
        }
        return 0;
    }

    // determines if there are 3 of the same pieces vertically
    private boolean checkVerticalWinner(){
        for (int i = 0; i < 3; i++) {
            if (boardMoves[0][i].equals(boardMoves[1][i]) && boardMoves[1][i].equals(boardMoves[2][i]) && (boardMoves[0][i].equals(boardMoves[2][i])))  {
                if (!boardMoves[0][i].equals(" ")){
                    return true;
                }
            }
        }
        return false;
    }

    // determines if there are 3 of the same pieces horizontally
    private boolean checkHorizontalWinner() {
        for (int i = 0; i < 3; i++) {
            if (boardMoves[i][0].equals(boardMoves[i][1]) && boardMoves[i][1].equals(boardMoves[i][2]) && (boardMoves[i][0].equals(boardMoves[i][2])))  {
                if (!boardMoves[i][0].equals(" ")){
                    return true;
                }
            }
        }
        return false;
    }

    // determines if there are 3 of the same pieces diagonally
    private boolean checkDiagonalWinner() {
        if (boardMoves[0][0].equals(boardMoves[1][1]) && boardMoves[1][1].equals(boardMoves[2][2]) && (boardMoves[2][2].equals(boardMoves[0][0]))){
            if (!boardMoves[0][0].equals(" ")){
                return true;
            }
        }

        if (boardMoves[0][2].equals(boardMoves[1][1]) && boardMoves[1][1].equals(boardMoves[2][0]) && (boardMoves[2][0].equals(boardMoves[0][2]))) {
            if (!boardMoves[0][2].equals(" ")){
                return true;
            }
        }

        return false;
    }

    private void clearBoard() {
        String[][] cleared = { {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "} };
        boardMoves = cleared;
    }
}

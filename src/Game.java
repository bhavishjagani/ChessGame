public class Game {
    private static Board board;
    private boolean gameOver;

    public void playGame() {
    }

    public static void move(int x1, int y1, int x2, int y2) {
        if (board.movePiece(x1, y1, x2, y2)) {
            System.out.println("Move is successful");
        } else {
            System.out.println("Invalid move");
        }
    }

    public static void main(String[] args) {
        board = new Board();
        board.initializeBoard();
        board.printBoard();

        // Attempt to move a white pawn from 6, 0 to 4, 0 (which is from a2 to a4)
        System.out.println("Trying to move white pawn from 6, 0 to 4, 0");
        move(0, 6, 0, 4);
        board.printBoard();

        // Attempt to move a black pawn from 1, 0 to 3, 0 (which is from a7 to a5)
        System.out.println("Trying to move black pawn from 1, 0 to 3, 0");
        move(0, 1, 0, 3);
        board.printBoard();

        //Attempt to move a white rook from 7, 0 to 5, 0 (which is from a1 to a3)
        System.out.println("Trying to move white rook from 7, 0 to 5, 0");
        move(0, 7, 0, 5);
        board.printBoard();

        //Attempt to move a black rook from 0, 0 to 2, 0 (which is from a8 to a6)
        System.out.println("Trying to move black rook from 0, 0 to 2, 0");
        move(0, 0, 0, 2);
        board.printBoard();

        //Attempt to move a white bishop from c1 to b2 to c3 to a5
        System.out.println("Trying to move a white bishop from c1 to b2 to c3 to a5");
        move(1, 6, 1, 5);
        move(2, 7, 1, 6);
        board.printBoard();
        move(1, 6, 2, 5);
        board.printBoard();
        move(2, 5, 0, 3);
        board.printBoard();
    }
}
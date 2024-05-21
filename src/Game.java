public class Game {
    private static Board board;
    private boolean gameOver;
    public void playGame() {}
    public static void move(int x1, int y1, int x2, int y2, Piece piece) {
        if (piece.isValidMove(x1, y1, x2, y2, board)) {
            board.board[x2][y2].setPiece(piece);
            System.out.println("Move is successful");
        }
    }
    public static void main (String[] args) {
        board = new Board();
        board.initializeBoard();
        board.printBoard();
        move(1, 6, 1, 5, board.board[1][6].getPiece());
        board.printBoard();
    }
}

//    Chess Piece Unicode Symbol (White) Unicode Symbol (Black) Java Code (White) Java Code (Black)
//        King ♔ ♚ '\u2654' '\u265A'
//        Queen ♕ ♛ '\u2655' '\u265B'
//        Rook ♖ ♜ '\u2656' '\u265C'
//        Bishop ♗ ♝ '\u2657' '\u265D'
//        Knight ♘ ♞ '\u2658' '\u265E'
//        Pawn ♙ ♟︎ '\u2659' '\u265F'

//print board, test with symbols, display board with symbols, white on bottom, black on top

//Check Pawn Validation, Make Pawn Move
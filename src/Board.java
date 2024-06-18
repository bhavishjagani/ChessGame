public class Board {
     Square board[][];
     boolean gameOver;
    public Board() {
        this.board = new Square[8][8];
        this.gameOver = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(new EmptyPiece(' ', PieceColor.EMPTY));
            }
        }
    }
    public void initializeBoard() {
        board[0][0].setPiece(new Rook('\u2656', PieceColor.BLACK));
        board[0][7].setPiece(new Rook('\u2656', PieceColor.BLACK));
        board[0][1].setPiece(new Knight('\u2658', PieceColor.BLACK));
        board[0][6].setPiece(new Knight('\u2658', PieceColor.BLACK));
        board[0][2].setPiece(new Bishop('\u2657', PieceColor.BLACK));
        board[0][5].setPiece(new Bishop('\u2657', PieceColor.BLACK));
        board[0][3].setPiece(new Queen('\u2655', PieceColor.BLACK));
        board[0][4].setPiece(new King('\u2654', PieceColor.BLACK, 0, 4));
        board[7][0].setPiece(new Rook('\u265C', PieceColor.WHITE));
        board[7][7].setPiece(new Rook('\u265C', PieceColor.WHITE));
        board[7][1].setPiece(new Knight('\u265E', PieceColor.WHITE));
        board[7][6].setPiece(new Knight('\u265E', PieceColor.WHITE));
        board[7][2].setPiece(new Bishop('\u265D', PieceColor.WHITE));
        board[7][5].setPiece(new Bishop('\u265D', PieceColor.WHITE));
        board[7][3].setPiece(new Queen('\u265B', PieceColor.WHITE));
        board[7][4].setPiece(new King('\u265A', PieceColor.WHITE, 7, 4));
        board[1][0].setPiece(new Pawn('\u2659', PieceColor.BLACK));//
        board[1][1].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][2].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][3].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][4].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][5].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][6].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[1][7].setPiece(new Pawn('\u2659', PieceColor.BLACK));
        board[6][0].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][1].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][2].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][3].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][4].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][5].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][6].setPiece(new Pawn('\u265F', PieceColor.WHITE));
        board[6][7].setPiece(new Pawn('\u265F', PieceColor.WHITE));
    }
    public boolean movePiece(int x1, int y1, int x2, int y2) {
        while (gameOver == false) {
            System.out.println("Attempting to move piece from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
            Piece piece = board[y1][x1].getPiece();
            if (piece == null || piece instanceof EmptyPiece) {
                System.out.println("No piece at the starting position (" + x1 + ", " + y1 + ")");
                return false;
            }
            System.out.println("Found piece: " + piece.getClass().getSimpleName());
            if (piece.isValidMove(x1, y1, x2, y2, this)) {
                // Move the piece
                board[y2][x2].setPiece(piece);
                board[y1][x1].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                System.out.println("Move successful");
                return true;
            } else {
                System.out.println("Invalid move for piece " + piece.getClass().getSimpleName() + " from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
            }
            return false;
        }
        return false;
    }
    public void printBoard() {
        String border = "  +-----+-------+-------+-------+-------+-------+-------+-------+";
        for (int i = 0; i < 8; i++) {
            System.out.println(border);
            System.out.print(8-i + " ");
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 1 || i == 6 || i == 7) {
                    System.out.print("|\t" + board[i][j].getPiece().getSymbol() + "\t");
                }
                else {
                    System.out.print("|\t" + board[i][j].getPiece().getSymbol() + "\t");
                }
            }
            System.out.println("|");
        }
        System.out.println(border);
        System.out.println("     a       b       c       d        e       f       g       h");
    }
}
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
        board[0][0].setPiece(new Rook('♖', PieceColor.BLACK));
        board[0][7].setPiece(new Rook('♖', PieceColor.BLACK));
        board[0][1].setPiece(new Knight('♘', PieceColor.BLACK));
        board[0][6].setPiece(new Knight('♘', PieceColor.BLACK));
        board[0][2].setPiece(new Bishop('♗', PieceColor.BLACK));
        board[0][5].setPiece(new Bishop('♗', PieceColor.BLACK));
        board[0][3].setPiece(new Queen('♕', PieceColor.BLACK));
        board[0][4].setPiece(new King('♔', PieceColor.BLACK, 0, 4));
        board[7][0].setPiece(new Rook('♜', PieceColor.WHITE));
        board[7][7].setPiece(new Rook('♜', PieceColor.WHITE));
        board[7][1].setPiece(new Knight('♞', PieceColor.WHITE));
        board[7][6].setPiece(new Knight('♞', PieceColor.WHITE));
        board[7][2].setPiece(new Bishop('♝', PieceColor.WHITE));
        board[7][5].setPiece(new Bishop('♝', PieceColor.WHITE));
        board[7][3].setPiece(new Queen('♛', PieceColor.WHITE));
        board[7][4].setPiece(new King('♚', PieceColor.WHITE, 7, 4));
        board[1][0].setPiece(new Pawn('♙', PieceColor.BLACK));//
        board[1][1].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][2].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][3].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][4].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][5].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][6].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[1][7].setPiece(new Pawn('♙', PieceColor.BLACK));
        board[6][0].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][1].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][2].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][3].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][4].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][5].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][6].setPiece(new Pawn('♟', PieceColor.WHITE));
        board[6][7].setPiece(new Pawn('♟', PieceColor.WHITE));
    }
    public boolean movePiece(int x1, int y1, int x2, int y2) {
        while (! gameOver) {
            Piece piece = board[y1][x1].getPiece();
            if (piece.isValidMove(y1, x1, y2, x2, this)) {
                // Move the piece
                board[y2][x2].setPiece(piece);
                board[y1][x1].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                return true;
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
public class Pawn extends Piece {
    public Pawn(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        int direction = getColor() == PieceColor.WHITE ? -1 : 1;
        int startRow = getColor() == PieceColor.WHITE ? 6 : 1;

        if (x1 == x2 && y2 == y1 + direction && ! board.board[y2][x2].hasPiece()) {
            return true;
        }

        if (x1 == x2 && y1 == startRow && y2 == y1 + 2 * direction && ! board.board[y2][x2].hasPiece() && !board.board[y1 + direction][x2].hasPiece()) {
            return true;
        }

        if (Math.abs(x2 - x1) == 1 && y2 == y1 + direction && board.board[y2][x2].hasPiece() && board.board[y2][x2].getPiece().getColor() != getColor()) {
            return true;
        }

        System.out.println("Invalid move: No valid conditions met");
        return false;
    }
}

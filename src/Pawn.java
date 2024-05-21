public class Pawn extends Piece {
    public Pawn(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        int direction = getColor().equals("W") ? -1 : 1;
        if (x2 == x1 && y2 == y1 + direction && board.board[x2][y2] != null) { //1 step move
            return true;
        }
        int startRow = getColor().equals("W") ? 6 : 1;
        if (x1 == startRow && x2 == x1 && y2 == y1 + 2*direction && board.board[x2][y2] != null) { //2 step move
            return true;
        }
        if (y2 == y1 + direction && (x2 == x1 + 1 || x2 == x1 - 1) && board.board[x2][y2] != null) { //Piece Capture
            return true;
        }
        return false;
    }
}
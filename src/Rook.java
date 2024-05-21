public class Rook extends Piece {
    public Rook(char symbol, PieceColor color) {
        super(symbol, color);
    }

    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        int direction = getColor().equals("W") ? -1 : 1;
        if (board.board[x2][y2].getPiece().getSymbol() == ' ') {
            int diff_x = Math.abs(x1 - x2);
            int diff_y = Math.abs(y1 - y2);
            if (y1 == y2 && diff_x > 0) {
                for (int i = 0; i < diff_x; i++) {
                    if (board.hasPiece(x2 + i, y2)) {
                        return false;
                    }
                }
                return true;
            }
            if (x1 == x2 && diff_y > 0) {
                for (int i = 0; i < diff_y; i++) {
                    if (board.hasPiece(x2, y2 + i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
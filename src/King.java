public class King extends Piece {
    private boolean hasMoved;
    int x;
    int y;
    public King(char symbol, PieceColor color, int y, int x) {
        super(symbol, color);
        this.x = x;
        this.y = y;
        this.hasMoved = false;
    }
    @Override
    public boolean isValidMove(int y1, int x1, int y2, int x2, Board board) {
        // Checks horizontal, vertical, and diagonal move for only 1 square and if final coordinates are empty or has a piece of different color
        return ((Math.abs(x2 - x1) == 1 && y2 - y1 == 0) || (Math.abs(y2 - y1) == 1 && x2 - x1 == 0) || (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 1))
                && (!board.board[y2][x2].hasPiece() || board.board[y2][x2].getPiece().getColor() != this.getColor());
    }
    public boolean hasMoved() {
        return hasMoved;
    }
    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
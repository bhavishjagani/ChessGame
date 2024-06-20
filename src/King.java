public class King extends Piece{
    int x;
    int y;
    public King(char symbol, PieceColor color, int x, int y) {
        super(symbol, color);
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        if (((Math.abs(x2-x1) == 1 && y2-y1 == 0) || (Math.abs(y2-y1) == 1 && x2-x1 == 0) || (Math.abs(x2-x1) == 1 && Math.abs(y2-y1) == 1)) && ((! board.board[y2][x2].hasPiece() || board.board[y2][x2].getPiece().getColor() != this.getColor()))) { //Checks horizontal, vertical, and diagonal move for only 1 square and if final coordinates are empty or has a piece of different color
            return true;
        }
//
        return false;
    }
    public String toString() {
        return getColor().toString() + " " + getSymbol();
    }
}
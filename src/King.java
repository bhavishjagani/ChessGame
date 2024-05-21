public class King extends Piece{
    public King(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        return false;
    }

}

public class EmptyPiece extends Piece{
    public EmptyPiece(char symbol, PieceColor color) {
        super(symbol, color);
    }
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        return false;
    }
}

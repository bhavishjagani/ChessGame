public class Square {
    private Piece piece;
    public Square(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece p) {
        piece = p;
    }
    public boolean hasPiece() {
        if ( !(piece instanceof EmptyPiece)){
            return true;
        }
        return false;
    }
}
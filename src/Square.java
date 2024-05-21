public class Square {
    private boolean hasPiece;
    int x;
    int y;
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
    public void removePiece() {
        hasPiece = false;
    }
    public boolean hasPiece() {
        return hasPiece;
    }
}
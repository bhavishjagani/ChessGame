public abstract class Piece{
    private final char symbol;
    private final PieceColor color;
    public Piece(char symbol, PieceColor color) {
        this.symbol = symbol;
        this.color = color;
    }
    public char getSymbol() {
        return symbol;//
    }
    public PieceColor getColor() {
        return color;
    }
    public abstract boolean isValidMove(int x1, int y1, int x2, int y2, Board board);
}
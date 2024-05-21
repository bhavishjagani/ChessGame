public abstract class Piece{
    private char symbol;
    private PieceColor color;

    public Piece(char symbol, PieceColor color) {
        this.symbol = symbol;
        this.color = color;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char s) {
        symbol = s;
    }
    public PieceColor getColor() {
        return color;
    }

    public abstract boolean isValidMove(int x1, int y1, int x2, int y2, Board board);
}
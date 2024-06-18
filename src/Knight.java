public class Knight extends Piece{
    public Knight(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        if (((Math.abs(x2-x1) == 2 && Math.abs(y2-y1) == 1) || (Math.abs(y2-y1) == 2 && Math.abs(x2-x1) == 1)) && (! board.board[y2][x2].hasPiece() || board.board[y2][x2].getPiece().getColor() != this.getColor())) { //Checks that knight is moving correctly and checking if final coordinates are empty or has a piece and color is opposite
            return true;
        }

        return false;
    }
}
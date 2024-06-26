public class Rook extends Piece {
    public Rook(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int y1, int x1, int y2, int x2, Board board) {

        if (x1 != x2 && y1 != y2) { //Ensuring if rook is moving in straight line
            return false;
        }
        int diff_x = Integer.signum(x2-x1); //signum, checks if difference between coordinates is positive (returns 1), negative (returns -1), or returns 0
        int diff_y = Integer.signum(y2 - y1);//""

        int x = x1 + diff_x; //Coordinates of the next square
        int y = y1 + diff_y; //""

        while (x != x2 || y != y2) { //Path Clearance Loop
            if (board.board[y][x].hasPiece()) { //Ensuring current square is empty
                return false;
            }
            x += diff_x;
            y += diff_y;
        }

        //No piece on destination square, rook can move, OR, if piece is of opponent color, then can capture (returns true)
        return !board.board[y2][x2].hasPiece() || board.board[y2][x2].getPiece().getColor() != this.getColor();
    }
}
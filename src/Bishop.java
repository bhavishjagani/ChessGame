public class Bishop extends Piece{
    public Bishop(char symbol, PieceColor color) {
        super(symbol, color);
    }
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2, Board board) {
        System.out.println("Bishop move validation from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
        System.out.println("Bishop color: " + getColor());

        if (Math.abs(x2-x1) != Math.abs(y2-y1)) { //Checking if different between x and y coordinates are equal
            return false;
        }

        int diff_x = Integer.signum(x2-x1); //signum, checks if difference between coordinates is positive (returns 1), negative (returns -1), or returns 0
        int diff_y = Integer.signum(y2 - y1);//""

        int x = x1 + diff_x; //Coordinates of the next square
        int y = y1 + diff_y; //""

        while (x != x2 || y != y2) { //Path Clearance Loop
            System.out.println(x + " " + y);
            if (board.board[y][x].hasPiece()) { //Ensuring current square is empty
                return false;
            }
            x += diff_x;
            y += diff_y;
        }

        if (! board.board[y2][x2].hasPiece() || board.board[y2][x2].getPiece().getColor() != this.getColor()) { //No piece on destination square, bishop can move, OR, if piece is of opponent color, then can capture (returns true)
            return true;
        }

        System.out.println("Invalid move: No valid conditions met");
        return false;
    }
}
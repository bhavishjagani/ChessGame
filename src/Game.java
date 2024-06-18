import java.util.Scanner;
public class Game {
    private static Board board;
    private static boolean isWhite = true;
    public static void move(int x1, int y1, int x2, int y2) {
        if (board.movePiece(x1, y1, x2, y2)) {
            System.out.println("Move is successful");
            isCheck();
            switchPlayer();
        }
        else {
            System.out.println("Invalid move.");
        }
    }
    public static void switchPlayer() {
        isWhite = isWhite ? false : true;
    }
    public static King getKing() {
        PieceColor currentPlayer = isWhite ? PieceColor.BLACK : PieceColor.WHITE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.board[i][j].getPiece().getColor() == currentPlayer && board.board[i][j].getPiece() instanceof King) {
                    return (King) board.board[i][j].getPiece();
                }
            }
        }
        return null;
    }
    public static boolean isCheck() {
        King opponentKing = getKing();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.board[i][j].hasPiece() && (board.board[i][j].getPiece().getColor() != opponentKing.getColor())) {
                    Piece piece = board.board[i][j].getPiece();
                    if (piece.isValidMove(i, j, opponentKing.x, opponentKing.y, board)) {
                        System.out.println(opponentKing.getColor().toString() + " " + "King is in check.");
                        System.out.println(i);
                        System.out.println(j);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting coordinates and destination coordinates in algebraic notation (a2,a4): ");
        String coordinates = sc.next();
        char startCoordinatesColumn = coordinates.charAt(0);
        char startCoordinatesRow = coordinates.charAt(1);
        char endCoordinatesColumn = coordinates.charAt(3);
        char endCoordinatesRow = coordinates.charAt(4);
        int x1 = 0;
        x1 = ((int) startCoordinatesColumn) - 97;
        int y1 = 0;
        y1 = Math.abs(8 - (Character.getNumericValue(startCoordinatesRow)));

        int x2 = 0;
        x2 = ((int) endCoordinatesColumn) - 97;
        int y2 = 0;
        y2 = Math.abs(8 - (Character.getNumericValue(endCoordinatesRow)));

        if ((isWhite && board.board[y1][x1].getPiece().getColor() == PieceColor.WHITE) || (!isWhite && board.board[y1][x1].getPiece().getColor() == PieceColor.BLACK)) {
            move(x1, y1, x2, y2);
        }
        else {
            System.out.println("Invalid Move. It is not your turn to move.");
        }
        board.printBoard();
    }
    public static void main(String[] args) {
        board = new Board();
        board.initializeBoard();
        board.printBoard();

        while (!board.gameOver) {
            String currentPlayer = isWhite ? "White" : "Black";
            System.out.println("\n" + currentPlayer + " Player Turn");
            getInput();
            System.out.println("\n");
        }
    }
}

//Update King Move Differnetly, X Y attributes should be updated
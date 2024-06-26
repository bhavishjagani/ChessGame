import java.util.*;
public class Game {
    private static Board board;
    private static boolean isWhite = true;
    public static void move(int x1, int y1, int x2, int y2) {
        if (board.movePiece(x1, y1, x2, y2)) {
            isCheck();
            if (isCheck()) {
                if (isWhite) {
                    System.out.println();
                    System.out.println("Black King is in Check.");
                }
                else {
                    System.out.println();
                    System.out.println("White King is in Check.");
                }
                isCheckmate();
                if (isCheckmate()) {
                    board.gameOver = true;
                    if (isWhite) {
                        System.out.println("White Player Has Won The Game! Checkmate!");
                        System.out.println();
                    }
                    else {
                        System.out.println("Black Player Has Won The Game! Checkmate!");
                        System.out.println();
                    }
                    System.exit(0);
                }
            }
            switchPlayer();
        }
        else {
            System.out.println("");
            System.out.println("Invalid Move.");
        }
    }
    public static void switchPlayer() {
        isWhite = !isWhite;
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
                    if (piece.isValidMove(i, j, opponentKing.y, opponentKing.x, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static List<ArrayList <Integer>> findLegalMoves(Piece piece, int pieceX, int pieceY) {
        List<ArrayList <Integer>> coordinates = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.isValidMove(pieceX, pieceY, i, j, board) && (board.board[i][j].getPiece().getColor() == board.board[pieceX][pieceY].getPiece().getColor())) {
                    ArrayList <Integer> source = new ArrayList<>();
                    ArrayList <Integer> destination = new ArrayList<>();
                    source.add(pieceX);
                    source.add(pieceY);
                    destination.add(i);
                    destination.add(j);
                    coordinates.add(source);
                    coordinates.add(destination);
                }
            }
        }
        return coordinates;
    }
    public static boolean isCheckmate() {
        King checkedKing = getKing();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.board[i][j].getPiece();
                if (piece.getColor().equals(checkedKing.getColor())) {
                    List<ArrayList<Integer>> legalMoves = findLegalMoves(piece, i, j);
                    int size = legalMoves.size();
                    int index = 0;
                    while (index < size) {
                        int sX = legalMoves.get(index).get(0);
                        int sY = legalMoves.get(index).get(1);
                        int dX = legalMoves.get(index + 1).get(0);
                        int dY = legalMoves.get(index + 1).get(1);
                        Piece capturePiece = board.board[dY][dX].getPiece();
                        board.board[dY][dX].setPiece(piece);
                        board.board[sY][sX].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                        if (! isCheck()) {
                            if (!(capturePiece instanceof EmptyPiece)) {
                                board.board[sY][sX].setPiece(board.board[dY][dX].getPiece());
                                board.board[dY][dX].setPiece(capturePiece);
                            }
                            return false;
                        }
                        index += 2;
                    }
                }
            }
        }
        return true;
    }
    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting coordinates and destination coordinates in algebraic notation (a2,a4): ");
        String coordinates = sc.next();
        char startCoordinatesColumn = coordinates.charAt(0);
        char startCoordinatesRow = coordinates.charAt(1);
        char endCoordinatesColumn = coordinates.charAt(3);
        char endCoordinatesRow = coordinates.charAt(4);
        int x1 = ((int) startCoordinatesColumn) - 97;
        int y1 = Math.abs(8 - (Character.getNumericValue(startCoordinatesRow)));

        int x2 = ((int) endCoordinatesColumn) - 97;
        int y2 = Math.abs(8 - (Character.getNumericValue(endCoordinatesRow)));


        if ((isWhite && board.board[y1][x1].getPiece().getColor() == PieceColor.WHITE) || (!isWhite && board.board[y1][x1].getPiece().getColor() == PieceColor.BLACK)) {
            move(x1, y1, x2, y2);
        }
        else {
            if (! board.board[x1][y1].hasPiece()) {
                System.out.println();
                System.out.println("Square is Empty.");
            }
            else {
                System.out.println("Invalid Move. It is not your turn to move.");
            }
        }
        System.out.println();
        board.printBoard();
    }
    public static void main(String[] args) {
        board = new Board();
        board.initializeBoard();
        board.printBoard();

        while (!board.gameOver) {
            String currentPlayer = isWhite ? "White" : "Black";
            System.out.println("\n" + currentPlayer + " Player's Turn");
            getInput();
            System.out.println();
        }
    }
}
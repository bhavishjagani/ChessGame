import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Game {
    private Board board;
    private PieceColor turn;
    private Scanner scanner;
    private List <Character> whiteCapturedPieces;
    private List <Character> blackCapturedPieces;

    public Game() {
        board = new Board();
        board.initializeBoard();
        scanner = new Scanner(System.in);
        turn = PieceColor.WHITE;
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
    }
    private void switchTurn() {
        turn = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }
    private boolean isCheck(PieceColor color) {
        int[] kingPos = findKing(color);
        if (kingPos == null) {
            return false;
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.board[row][col].getPiece();
                if (piece != null && piece.getColor() != color) {
                    if (piece.isValidMove(row, col, kingPos[0], kingPos[1], board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean isCheckMate(PieceColor color) {
        if (!isCheck(color)) {
            return false;
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.board[row][col].getPiece();
                if (piece != null && piece.getColor() == color) {
                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            if (piece.isValidMove(row, col, r, c, board)) {
                                Piece originalPiece = board.board[r][c].getPiece();
                                board.board[r][c].setPiece(piece);
                                board.board[row][col].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                                if (!isCheck(color)) {
                                    board.board[row][col].setPiece(piece);
                                    board.board[r][c].setPiece(originalPiece);
                                    return false;
                                }
                                board.board[row][col].setPiece(piece);
                                board.board[r][c].setPiece(originalPiece);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    private int[] findKing(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.board[row][col].getPiece();
                if (piece instanceof King && piece.getColor() == color) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }
    private boolean move(int[] source, int[] dest) {
        Piece piece = board.board[source[0]][source[1]].getPiece();
        if (piece != null && piece.getColor() == turn) {
            if (piece.isValidMove(source[0], source[1], dest[0], dest[1], board)) {
                Piece destPiece = board.board[dest[0]][dest[1]].getPiece();
                if (! (destPiece instanceof EmptyPiece)) {
                    System.out.println(destPiece.getSymbol() + " has been captured.");
                    if (turn == PieceColor.WHITE) {
                        whiteCapturedPieces.add(destPiece.getSymbol());
                    }
                    else {
                        blackCapturedPieces.add(destPiece.getSymbol());
                    }

                }
                board.board[dest[0]][dest[1]].setPiece(piece);
                board.board[source[0]][source[1]].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                if (isCheck(turn)) {
                    System.out.println("Move places " + turn + " in check!");
                    board.board[source[0]][source[1]].setPiece(piece);
                    board.board[dest[0]][dest[1]].setPiece(destPiece);
                    return false;
                }
                switchTurn();
                return true;
            }
            else {
                System.out.println("Illegal move!");
            }
        }
        else {
            System.out.println("No piece at source or not your turn!");
        }
        return false;
    }
    public void play() {
        while (true) {
            board.printBoard();
            if (isCheckMate(turn)) {
                System.out.println("Checkmate! " + turn + " loses.");
                break;
            }
            if (isCheck(turn)) {
                System.out.println(turn + " is in check!");
            }
            System.out.println(turn + "'s move");
            System.out.println("White Captured Pieces: " + whiteCapturedPieces.toString());
            System.out.println("Black Captured Pieces: " + blackCapturedPieces.toString());
            System.out.print("Enter source and destination (e.g., 'e2,e4'): ");
            String[] input = scanner.nextLine().trim().split(",");
            if (input.length != 2) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            int[] source = parsePosition(input[0]);
            int[] dest = parsePosition(input[1]);
            if (source == null || dest == null) {
                System.out.println("Invalid positions. Try again.");
                continue;
            }
            move(source, dest);
        }
        scanner.close();
    }
    private int[] parsePosition(String pos) {
        if (pos.length() != 2) {
            return null;
        }
        int x = pos.charAt(0) - 'a';
        int y = 8 - (pos.charAt(1) - '0');
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return null;
        }
        return new int[]{y, x};
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
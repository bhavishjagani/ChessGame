import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game {
    private final Board board;
    private PieceColor turn;
    private final Scanner scanner;
    private final List<Character> whiteCapturedPieces;
    private final List<Character> blackCapturedPieces;
    private final BoardLog boardLog;
    private final ChessTimer timer;
    private boolean whiteHasCastled;
    private boolean blackHasCastled;
    public Game(int totalMinutes) {
        board = new Board();
        turn = PieceColor.WHITE;
        boardLog = new BoardLog();
        boardLog.writeLog();
        board.initializeBoard();
        scanner = new Scanner(System.in);
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        timer = new ChessTimer(totalMinutes);
        timer.switchTurn(turn);
    }
    private void switchTurn() {
        turn = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        timer.switchTurn(turn);
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
    private boolean canCastle(PieceColor color, boolean isKingSide) {
        int kingRow = (color == PieceColor.WHITE) ? 7 : 0;
        int kingCol = 4; // King's initial column

        King king = (King) board.board[kingRow][kingCol].getPiece();
        if (king == null || king.hasMoved()) {
            return false; // King is either not present or has moved
        }

        Rook rook;
        int rookCol;
        if (isKingSide) {
            rookCol = 7; // King's side Rook column
        }
        else {
            rookCol = 0; // Queen's side Rook column
        }

        rook = (Rook) board.board[kingRow][rookCol].getPiece();
        if (rook == null || rook.hasMoved()) {
            return false; // Rook is either not present or has moved
        }

        int step = (isKingSide) ? 1 : -1;
        for (int col = kingCol + step; col != rookCol; col += step) {
            if (board.board[kingRow][col].hasPiece()) {
                return false; // There is a piece between King and Rook
            }
        }

        // Check if King or Rook moves through a square under attack
        int[] kingMoves = (isKingSide) ? new int[]{kingCol + 1, kingCol + 2} : new int[]{kingCol - 1, kingCol - 2};
        for (int col : kingMoves) {
            if (isUnderAttack(color, kingRow, col)) {
                return false; // King moves through an attacked square
            }
        }

        // King and Rook are both in their initial positions and all checks are passed
        return true;
    }
    private boolean isUnderAttack(PieceColor color, int row, int col) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.board[r][c].getPiece();
                if (piece != null && piece.getColor() != color && piece.isValidMove(r, c, row, col, board)) {
                    return true; // Square is attacked
                }
            }
        }
        return false;
    }
    private void move(int[] source, int[] dest, String move) {
        Piece piece = board.board[source[0]][source[1]].getPiece();
        if (piece != null && piece.getColor() == turn) {
            if (piece instanceof King && Math.abs(dest[1] - source[1]) == 2 && ! isCheck(turn)) {
                // Castling logic
                boolean isKingSide = dest[1] > source[1];
                if (canCastle(piece.getColor(), isKingSide)) {
                    int rookCol = isKingSide ? 7 : 0;
                    Rook rook = (Rook) board.board[source[0]][rookCol].getPiece();
                    int rookDestCol = isKingSide ? 5 : 3;

                    // Perform castling move
                    board.board[source[0]][source[1]].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                    board.board[source[0]][dest[1]].setPiece(piece);
                    if (turn == PieceColor.WHITE) {
                        whiteHasCastled = true;
                    }
                    else {
                        blackHasCastled = true;
                    }
                    ((King) piece).setMoved(true);

                    board.board[source[0]][rookCol].setPiece(new EmptyPiece(' ', PieceColor.EMPTY));
                    board.board[source[0]][rookDestCol].setPiece(rook);
                    rook.setMoved(true);

                }
                else {
                    System.out.println("Castling is not allowed!");
                }
                return; // Exit method after castling
            }
            if (piece.isValidMove(source[0], source[1], dest[0], dest[1], board)) {
                if (piece instanceof King && ((turn == PieceColor.WHITE && !whiteHasCastled) || (turn == PieceColor.BLACK && !blackHasCastled))) {
                    ((King) piece).setMoved(true);
                }
                if (piece instanceof Rook && ((turn == PieceColor.WHITE && !whiteHasCastled) || (turn == PieceColor.BLACK && !blackHasCastled))) {
                    ((Rook) piece).setMoved(true);
                }
                //Checks if piece is Rook/King, White/Black, and if they haven't castled and are trying to move, then set "hasMoved" as true, canCastle will then return false
                Piece destPiece = board.board[dest[0]][dest[1]].getPiece();
                if (!(destPiece instanceof EmptyPiece)) {
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
                }
                else {
                    boardLog.addLog(move, turn);
                    boardLog.writeLog();
                    switchTurn();
                }
            }
            else {
                System.out.println("Illegal move!");
            }
        }
        else {
            System.out.println("No piece at source or not your turn!");
        }
    }
    public void play() throws InterruptedException {
        while (true) {
            board.printBoard();
            if (isCheckMate(turn)) {
                System.out.println("Checkmate! " + turn + " loses.");
                break;
            }
            if (isCheck(turn)) {
                System.out.println(turn + " is in check!");
            }
            System.out.println();
            System.out.println(turn + "'s move");
            System.out.println();
            System.out.println("White Captured Pieces: " + whiteCapturedPieces);
            System.out.println("Black Captured Pieces: " + blackCapturedPieces);
            System.out.println();
            timer.printTime();
            System.out.println();
            System.out.println("Board Log: ");
            System.out.println();
            boardLog.printLog();
            System.out.println();
            System.out.print("Enter source and destination (e.g., 'e2,e4'): ");
            String[] input = scanner.nextLine().trim().split(",");
            String move = Arrays.toString(input);
            if ((! move.equals("[R]")) && (! move.equals("[D]")) && input.length != 2) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            if (move.equals("[R]")) {
                if (turn == PieceColor.WHITE) {
                    System.out.println("\nWhite Player Resigns. Black Player Wins!");
                }
                else {
                    System.out.println("\nBlack Player Resigns. White Player Wins!");
                }
                TimeUnit.SECONDS.sleep(3);
                System.exit(0);
            }
            if (move.equals("[D]")) {
                PieceColor oppositeColor = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
                System.out.println("\nThe " + turn.toString() + " Player is asking for a draw. Does the " + oppositeColor + " accept?: ");
                String answer = scanner.next();
                if (answer.equals("Y")) {
                    System.out.println(oppositeColor + " Player has accepted the draw. The game is a draw.");
                    TimeUnit.SECONDS.sleep(3);
                    System.exit(0);
                }
                else {
                    System.out.println(oppositeColor + " Player does not accept the draw.");
                }
            }
            if (! move.equals("[D]")) {
                int[] source = parsePosition(input[0]);
                int[] dest = parsePosition(input[1]);
                if (source == null || dest == null) {
                    System.out.println("Invalid positions. Try again.");
                    continue;
                }
                move(source, dest, move);
            }
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
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many minutes long would you like your game to be?: ");
        int totalMinutes = sc.nextInt();
        System.out.println();
        System.out.println("If at any point you want to resign, enter 'R' when are you inputting your move.");
        System.out.println("If at any point you want to draw, enter 'D'. If the opposing player accepts the draw, enter 'Y'. Otherwise, enter anything else and the game will continue.");
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        Game game = new Game(totalMinutes);
        game.play();
    }
}
//Add a special feature
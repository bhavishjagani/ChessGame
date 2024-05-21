public class Rules {
//    Chess Project V1:

//    You need to review the rules and movements of each chess piece: Pawns, Rooks, Knights, Bishops, Queens, and Kings then try to write Piece Classes this week for your Homework, We will discuss more about it in the session before we continue binary trees.

//    Project Details: The Java Chess Game project embarks on the timeless game of chess, crafting a fully functional console-based application. In this initial phase, our focus is on establishing a solid foundation, allowing a player to engage with an intelligent computer opponent within the terminal.
//    The primary goal for the first version is to implement a console-based chess game in Java that supports interactive play between a human user and the computer. This version will serve as the core upon which subsequent features and interfaces will be built.

//    Functional Requirements:

//    Accurate representation of chess rules for piece movements, including special moves like castling and en passant.
//    A console interface that displays the chessboard and captures user input for moves.
//    Implementation of a simple AI for the computer opponent capable of responding to player moves.
//    A move log system that records each move in a text file, providing a game history that can be reviewed.
//    Chess Pieces:
//    The queen can move horizontally, vertically, and diagonally across the board.
//    A rook can move horizontally and vertically across the board.
//    A bishop can move diagonally across the board.
//    A knight can jump to eight different squares which are two steps forward plus one step sideways from its current position.
//    The king can move in any direction, but only one step at a time. Also, the king must never move into check. There is also a special "castling" move for the king.
//    A pawn can move only forward towards the end of the board, but captures sideways. From its initial position, a pawn may make two steps, otherwise only a single step at a time. If the pawn reaches the end of the board, it is automatically promoted to another piece (usually a queen). There is also a special "en passant" move for the pawn.
//    Special Moves
//    "Castling" is a special move in the game of chess involving the king and either of the original rooks of the same color. It is the only move in chess (except promotion) in which a player moves two pieces at the same time. Castling consists of moving the king two squares towards a rook on the player’s first rank (row), then moving the rook onto the square over which the king crossed. Castling can only be done if the king has never moved, the rook involved has never moved, the squares between the king and the rook involved are not occupied, the king is not in check, and the king does not cross over or end on a square in which it would be in check.
//    "En passant" is a special pawn capture which can occur immediately after a player moves a pawn two squares forward from its starting position, and an enemy pawn could have captured it had the same pawn moved only one square forward. The opponent captures the just-moved pawn as if taking it "as it passes" through the first square. The resulting position is the same as if the pawn had moved only one square forward and the enemy pawn had captured normally.
//    The en passant capture must be done on the very next turn, or the right to do so is lost. Such a move is the only occasion in chess in which a piece captures but does not move to the square of the captured piece. If an en passant capture is the only legal move available, it must be made.
//    The chess game ends as soon as one king is trapped in "checkmate". That is, there is no move for the player possible which would get his king out of check. Then the player loses.

//    Program Specification

//    In this project, we want a software program to be built, where a human player can interactively play chess against the computer. The human user chooses a color (white or black) and the computer plays the opponent.
//    Basic functions that are required:

//    1. The game follows the official rules of chess. The program shows a game interface where the player can see the game board and make moves.
//    2. The program supports an interactive player (human user) and an automatic player (computer).
//    3. The human user chooses the side to play (white or black).
//    4. The program keeps a human-readable log of all the moves (in a text file).
//    5. The computer player makes its moves in reasonable time (less than 1 minute per move).
}

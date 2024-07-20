import java.util.Timer;
import java.util.TimerTask;
public class ChessTimer {
    private int whitePlayerTime;
    private int blackPlayerTime;
    private Timer timer;
    private PieceColor currentTurn;
    public ChessTimer(int totalMinutes) {
        this.whitePlayerTime = totalMinutes * 60;
        this.blackPlayerTime = totalMinutes * 60;
        this.timer = new Timer();
    }
    public void switchTurn(PieceColor turn) {
        currentTurn = turn;
    }
    public void startTimer(PieceColor turn, Runnable onTimeout) {
        currentTurn = turn;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentTurn == PieceColor.WHITE) {
                    whitePlayerTime--;
                    if (whitePlayerTime <= 0) {
                        timer.cancel();
                        onTimeout.run();
                        System.out.println("White Player's time is up! Black wins!");
                    }
                }
                else {
                    blackPlayerTime--;
                    if (blackPlayerTime <= 0) {
                        timer.cancel();
                        onTimeout.run();
                        System.out.println("Black Player's time is up! White wins!");
                    }
                }
            }
        }, 0, 1000);
    }
    public void printTime() {
        System.out.println("White Time: " + formatTime(whitePlayerTime) + " | Black Time: " + formatTime(blackPlayerTime));
    }

    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

}
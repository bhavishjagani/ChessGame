import java.util.Timer;
import java.util.TimerTask;
public class ChessTimer {
    private int whitePlayerTime;
    private int blackPlayerTime;
    private final Timer timer;
    private PieceColor currentTurn;
    private TimerTask timerTask;
    public ChessTimer(int totalMinutes) {
        this.whitePlayerTime = totalMinutes * 60;
        this.blackPlayerTime = totalMinutes * 60;
        this.timer = new Timer();
    }
    public void switchTurn(PieceColor turn) {
        currentTurn = turn;
        if (timerTask != null) {
            timerTask.cancel();
        }
        startTimer(turn);
    }
    public void startTimer(PieceColor turn) {
        currentTurn = turn;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (currentTurn == PieceColor.WHITE) {
                    whitePlayerTime--;
                    if (whitePlayerTime <= 0) {
                        timer.cancel();
                        System.out.println("\n\nWhite Player's time is up! Black wins!");
                        System.exit(0);
                    }
                }
                else {
                    blackPlayerTime--;
                    if (blackPlayerTime <= 0) {
                        timer.cancel();
                        System.out.println("\n\nBlack Player's time is up! White wins!");
                        System.exit(0);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BoardLog {
    private final List<String> logs;
    static int step;
    public BoardLog() {
        logs = new ArrayList<>();
        step = 0;
    }
    public void addLog(String move, PieceColor color) {
        if (color == PieceColor.WHITE) {
            step += 1;
            String stringBuilder = step +
                    ". " +
                    color +
                    ": " +
                    move.substring(1, 7);
            logs.add(stringBuilder);
        }
        else {
            String stringBuilder = "   " +
                    color +
                    ": " +
                    move.substring(1, 7);
            logs.add(stringBuilder);
        }
    }
    public void printLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader("BoardLogTextFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            if (! logs.isEmpty()) {
                System.out.println("There is an error. " + e.getMessage());
            }
        }
    }
    public void writeLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BoardLogTextFile.txt"))) {
            for (String move : logs) {
                writer.write(move);
                writer.write("\n");
            }
        }
        catch (IOException e) {
            System.out.println("There is an error. " + e.getMessage());
        }
    }
}
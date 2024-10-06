import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface for Timer Display
interface TimerDisplay {
    void updateDisplay(String time);
}

// GUI Implementation of TimerDisplay
class SwingTimerDisplay implements TimerDisplay {
    private JLabel timeLabel;

    public SwingTimerDisplay(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void updateDisplay(String time) {
        timeLabel.setText(time);
    }
}

// Interface for Stopwatch Actions
interface StopwatchActions {
    void start();
    void stop();
    void reset();
}

// Stopwatch Class
class Stopwatch implements StopwatchActions, ActionListener {
    private Timer timer;
    private int elapsedSeconds;
    private TimerDisplay timerDisplay;

    public Stopwatch(TimerDisplay timerDisplay) {
        this.timerDisplay = timerDisplay;
        this.timer = new Timer(1000, this);
        this.elapsedSeconds = 0;
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void reset() {
        timer.stop();
        elapsedSeconds = 0;
        timerDisplay.updateDisplay(formatTime(elapsedSeconds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedSeconds++;
        timerDisplay.updateDisplay(formatTime(elapsedSeconds));
    }

    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

// Main Class to run the Stopwatch
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JLabel timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(timeLabel, BorderLayout.CENTER);

        SwingTimerDisplay timerDisplay = new SwingTimerDisplay(timeLabel);
        Stopwatch stopwatch = new Stopwatch(timerDisplay);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        startButton.addActionListener(e -> stopwatch.start());
        stopButton.addActionListener(e -> stopwatch.stop());
        resetButton.addActionListener(e -> stopwatch.reset());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        frame.setVisible(true);
    }
}

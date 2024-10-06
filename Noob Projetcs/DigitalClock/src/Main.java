import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Interface for displaying time
interface TimeDisplay {
    void showTime(String time);
}

// Implementation of TimeDisplay interface for GUI
class GUIDisplay implements TimeDisplay {
    private JLabel timeLabel;

    public GUIDisplay(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void showTime(String time) {
        timeLabel.setText(time);
    }
}

// Interface for formatting time
interface TimeFormatter {
    String formatTime(Date date);
}

// Implementation of TimeFormatter interface
class SimpleTimeFormatter implements TimeFormatter {
    private SimpleDateFormat simpleDateFormat;

    public SimpleTimeFormatter(String pattern) {
        this.simpleDateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String formatTime(Date date) {
        return simpleDateFormat.format(date);
    }
}

// Digital Clock class
class DigitalClock {
    private TimeFormatter timeFormatter;
    private TimeDisplay timeDisplay;

    public DigitalClock(TimeFormatter timeFormatter, TimeDisplay timeDisplay) {
        this.timeFormatter = timeFormatter;
        this.timeDisplay = timeDisplay;
    }

    public void start() {
        Timer timer = new Timer(1000, _ -> {
            Date now = new Date();
            String formattedTime = timeFormatter.formatTime(now);
            timeDisplay.showTime(formattedTime);
        });
        timer.start();
    }
}

// Main class to run the clock
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Digital Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 80));
        frame.add(timeLabel);

        TimeFormatter timeFormatter = new SimpleTimeFormatter("HH:mm:ss");
        TimeDisplay timeDisplay = new GUIDisplay(timeLabel);
        DigitalClock clock = new DigitalClock(timeFormatter, timeDisplay);

        clock.start();

        frame.setVisible(true);
    }
}

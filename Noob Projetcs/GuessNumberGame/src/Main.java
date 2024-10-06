import java.util.Scanner;
import java.util.Random;

// Interface for User Input
interface UserInput {
    int getGuess();
}

// Implementation of UserInput interface for Console
class ConsoleInput implements UserInput {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int getGuess() {
        System.out.println("Enter your guess: ");
        return scanner.nextInt();
    }
}

// Interface for Random Number Generator
interface NumberGenerator {
    int generateNumber();
}

// Implementation of NumberGenerator interface
class RandomNumberGenerator implements NumberGenerator {
    private Random random = new Random();

    @Override
    public int generateNumber() {
        return random.nextInt(100) + 1;  // Number between 1 and 100
    }
}

// Class for the Game Logic
class GuessTheNumberGame {
    private final NumberGenerator numberGenerator;
    private final UserInput userInput;

    public GuessTheNumberGame(NumberGenerator numberGenerator, UserInput userInput) {
        this.numberGenerator = numberGenerator;
        this.userInput = userInput;
    }

    public void play() {
        int targetNumber = numberGenerator.generateNumber();
        int guess;
        int attempts = 0;

        do {
            guess = userInput.getGuess();
            attempts++;
            if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
            }
        } while (guess != targetNumber);
    }
}

// Main class to run the game
public class Main {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        UserInput userInput = new ConsoleInput();
        GuessTheNumberGame game = new GuessTheNumberGame(numberGenerator, userInput);
        game.play();
    }
}

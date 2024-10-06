import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.println("Enter an operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        System.out.println("Enter second number: ");
        double num2 = scanner.nextDouble();

        Calculator calculator = new Calculator();
        Operation operation;

        switch (operator) {
            case '+':
                operation = new Addition();
                break;
            case '-':
                operation = new Subtraction();
                break;
            case '*':
                operation = new Multiplication();
                break;
            case '/':
                operation = new Division();
                break;
            default:
                System.out.println("Error! Invalid operator.");
                return;
        }

        try {
            double result = calculator.calculate(num1, num2, operation);
            System.out.println("The result is: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
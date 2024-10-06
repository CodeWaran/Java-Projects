import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
[]
// Interface for User Input
interface UserInput {
    double getAmount();
    String getCurrency();
}

// Implementation of UserInput interface for Console
class ConsoleInput implements UserInput {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public double getAmount() {
        System.out.println("Enter the amount: ");
        return scanner.nextDouble();
    }

    @Override
    public String getCurrency() {
        System.out.println("Enter the currency code (e.g.,INR, USD, EUR): ");
        return scanner.next().toUpperCase();
    }
}

// Interface for Currency Conversion
interface CurrencyConverter {
    double convert(double amount, String fromCurrency, String toCurrency);
}

// Implementation of CurrencyConverter interface
class SimpleCurrencyConverter implements CurrencyConverter {
    private Map<String, Double> exchangeRates;

    public SimpleCurrencyConverter() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0); // Assume USD as base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("INR", 74.5);
        // Add more currencies as needed
    }

    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        double amountInUsd = amount / exchangeRates.get(fromCurrency);
        return amountInUsd * exchangeRates.get(toCurrency);
    }
}

// Class for handling the Currency Conversion
class CurrencyConversionHandler {
    private final CurrencyConverter currencyConverter;
    private final UserInput userInput;

    public CurrencyConversionHandler(CurrencyConverter currencyConverter, UserInput userInput) {
        this.currencyConverter = currencyConverter;
        this.userInput = userInput;
    }

    public void performConversion() {
        double amount = userInput.getAmount();
        String fromCurrency = userInput.getCurrency();
        String toCurrency = userInput.getCurrency();

        try {
            double convertedAmount = currencyConverter.convert(amount, fromCurrency, toCurrency);
            System.out.println("Converted amount: " + convertedAmount + " " + toCurrency);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Main class to run the converter
public class Main {
    public static void main(String[] args) {
        UserInput userInput = new ConsoleInput();
        CurrencyConverter currencyConverter = new SimpleCurrencyConverter();
        CurrencyConversionHandler conversionHandler = new CurrencyConversionHandler(currencyConverter, userInput);
        conversionHandler.performConversion();
    }
}

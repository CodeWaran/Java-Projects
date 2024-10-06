// Interface for operations
interface Operation {
    double apply(double a, double b);
}

// Concrete operation classes
class Addition implements Operation {
    @Override
    public double apply(double a, double b) {
        return a + b;
    }
}

class Subtraction implements Operation {
    @Override
    public double apply(double a, double b) {
        return a - b;
    }
}

class Multiplication implements Operation {
    @Override
    public double apply(double a, double b) {
        return a * b;
    }
}

class Division implements Operation {
    @Override
    public double apply(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}

// Calculator class
class Calculator {
    public double calculate(double num1, double num2, Operation operation) {
        return operation.apply(num1, num2);
    }
}

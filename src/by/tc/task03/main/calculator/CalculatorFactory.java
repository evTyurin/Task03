package by.tc.task03.main.calculator;

public class CalculatorFactory {
    private static CalculatorFactory instance = new CalculatorFactory();

    private final Calculatable calculator = new CalculatorImpl();

    private CalculatorFactory() {};

    public Calculatable getCalculator() {
        return calculator;
    }

    public static CalculatorFactory getInstance() {
        if(instance == null) {
            instance = new CalculatorFactory();
        }
        return instance;
    }
}

package by.tc.task03.main.calculator;

public class Factory {
    private static Factory instance = new Factory();

    private final Calculatable calculator = new CalculatorImpl();

    private Factory() {};

    public Calculatable getCalculator() {
        return calculator;
    }

    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }
}

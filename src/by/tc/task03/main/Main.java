package by.tc.task03.main;

import by.tc.task03.main.calculator.Factory;

public class Main {

    public static void main(String[] args)  {

        double result = Factory.getInstance().getCalculator().calculate("122 - 34 - 3* (55 + 5* (3 - 2)) * 2");

        System.out.println(result);

    }

}

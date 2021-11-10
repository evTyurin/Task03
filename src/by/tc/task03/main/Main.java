package by.tc.task03.main;

public class Main {

    public static void main(String[] args)  {

        String result;

        result = Calc.getInstance().getCalc("(3 + 3) * 3 - 5");

        System.out.println(result);

    }
}

package by.tc.task03.main.calculator;

public class CalculatorImpl implements Calculatable{

    CalculatorImpl() {}

    @Override
    public double calculate (String equation) {

        LexemeXMLParser lexemeRepository = new LexemeXMLParser(equation);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemeRepository.getManyLexemas());
        LexemeCalculator counting = new LexemeCalculator();

        return counting.expr(lexemeBuffer);
    }

}

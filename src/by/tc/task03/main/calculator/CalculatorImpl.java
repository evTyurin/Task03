package by.tc.task03.main.calculator;

import by.tc.task03.main.calculator.entity.LexemeBuffer;
import by.tc.task03.main.calculator.servise.LexemeCalculator;
import by.tc.task03.main.calculator.servise.LexemeXMLParser;

public class CalculatorImpl implements Calculatable{

    CalculatorImpl() {}

    @Override
    public double calculate (String equation) {

        LexemeXMLParser lexemeRepository = LexemeXMLParser.getInstance(equation);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemeRepository.getLexemes());
        LexemeCalculator calculator = LexemeCalculator.getInstance();

        return calculator.solve(lexemeBuffer);
    }

}

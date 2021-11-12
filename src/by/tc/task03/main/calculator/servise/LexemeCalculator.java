package by.tc.task03.main.calculator.servise;

import by.tc.task03.main.calculator.entity.LexemeBean;
import by.tc.task03.main.calculator.entity.LexemeBuffer;
import by.tc.task03.main.calculator.entity.LexemeType;

public class LexemeCalculator {

    private static LexemeCalculator instance;

    public static LexemeCalculator getInstance() {
        instance = new LexemeCalculator();
        return instance;
    }

    LexemeCalculator() {}

    public double solve(LexemeBuffer lexemes) {
        LexemeBean lexeme = lexemes.next();
        if (lexeme.getType() == LexemeType.END_OF_EQUATION) {
            return 0;
        } else {
            lexemes.back();
            return solvePlusAndMinus(lexemes);
        }
    }

    private double solvePlusAndMinus(LexemeBuffer lexemes) {
        double value = solveMultiplicationAndDivision(lexemes);
        while (true) {
            LexemeBean lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case PLUS:
                    value += solveMultiplicationAndDivision(lexemes);
                    break;
                case MINUS:
                    value -= solveMultiplicationAndDivision(lexemes);
                    break;
                case END_OF_EQUATION:
                case RIGHT_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPosition());
            }
        }
    }

    private double solveMultiplicationAndDivision(LexemeBuffer lexemes) {
        double value = solveBrackets(lexemes);
        while (true) {
            LexemeBean lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case MULTIPLICATION:
                    value *= solveBrackets(lexemes);
                    break;
                case DIVISION:
                    value /= solveBrackets(lexemes);
                    break;
                case END_OF_EQUATION:
                case RIGHT_BRACKET:
                case PLUS:
                case MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPosition());
            }
        }
    }

    private double solveBrackets(LexemeBuffer lexemes) {
        LexemeBean lexeme = lexemes.next();
        switch (lexeme.getType()) {
            case NUMBER:
                return Double.parseDouble(lexeme.getValue());
            case LEFT_BRACKET:
                double value = solvePlusAndMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPosition());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                        + " at position: " + lexemes.getPosition());
        }
    }
}

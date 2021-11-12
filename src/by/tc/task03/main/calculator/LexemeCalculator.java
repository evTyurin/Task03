package by.tc.task03.main.calculator;

public class LexemeCalculator {

    LexemeCalculator() {}

    public int expr(LexemeBuffer lexemes) {
        LexemeBean lexeme = lexemes.next();
        if (lexeme.getType() == LexemeType.END_OF_EQUATION) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    private int plusminus(LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        System.out.println("value = " + value);
        while (true) {
            LexemeBean lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case PLUS:
                    value += multdiv(lexemes);
                    break;
                case MINUS:
                    value -= multdiv(lexemes);
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

    private int multdiv(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            LexemeBean lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case MULTIPLICATION:
                    value *= factor(lexemes);
                    break;
                case DIVISION:
                    value /= factor(lexemes);
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

    private int factor(LexemeBuffer lexemes) {
        LexemeBean lexeme = lexemes.next();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                int value = plusminus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue() //TODO
                            + " at position: " + lexemes.getPosition());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.getValue() //TODO
                        + " at position: " + lexemes.getPosition());
        }
    }
}

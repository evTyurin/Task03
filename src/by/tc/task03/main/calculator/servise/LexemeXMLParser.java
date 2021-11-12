package by.tc.task03.main.calculator.servise;

import by.tc.task03.main.calculator.entity.LexemeBean;
import by.tc.task03.main.calculator.entity.LexemeType;

import java.util.ArrayList;
import java.util.List;

public class LexemeXMLParser {
    public final char MIN_VALUE = '0';
    public final char MAX_VALUE = '9';
    public final char LEFT_BRACKET = '(';
    public final char RIGHT_BRACKET = ')';
    public final char PLUS = '+';
    public final char MINUS = '-';
    public final char MULTIPLICATION = '*';
    public final char DIVISION = '/';
    public final char EMPTY_PLACE = ' ';

    private static LexemeXMLParser instance;
    private List<LexemeBean> lexemes;

    public static LexemeXMLParser getInstance(String equation) {
        instance = new LexemeXMLParser(equation);
        return instance;
    }

    LexemeXMLParser(String equation) {
        this.lexemes = lexAnalyze(equation);
    }

    public List getLexemes() {
        return lexemes;
    }

    private List<LexemeBean> lexAnalyze(String expText) {
        int position = 0;
        ArrayList<LexemeBean> lexemes = new ArrayList<>();
        while (position< expText.length()) {
            char character = expText.charAt(position);
            switch (character) {
                case LEFT_BRACKET:
                    lexemes.add(new LexemeBean(LexemeType.LEFT_BRACKET, character));
                    position++;
                    continue;
                case RIGHT_BRACKET:
                    lexemes.add(new LexemeBean(LexemeType.RIGHT_BRACKET, character));
                    position++;
                    continue;
                case PLUS:
                    lexemes.add(new LexemeBean(LexemeType.PLUS, character));
                    position++;
                    continue;
                case MINUS:
                    lexemes.add(new LexemeBean(LexemeType.MINUS, character));
                    position++;
                    continue;
                case MULTIPLICATION:
                    lexemes.add(new LexemeBean(LexemeType.MULTIPLICATION, character));
                    position++;
                    continue;
                case DIVISION:
                    lexemes.add(new LexemeBean(LexemeType.DIVISION, character));
                    position++;
                    continue;
                default:
                    if (character <= MAX_VALUE && character >= MIN_VALUE) {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(character);
                            position++;
                            if (position >= expText.length()) {
                                break;
                            }
                            character = expText.charAt(position);
                        } while (character <= MAX_VALUE && character >= MIN_VALUE);
                        lexemes.add(new LexemeBean(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (character != EMPTY_PLACE) {
                            throw new RuntimeException("Unexpected character: " + character);
                        }
                        position++;
                    }
            }
        }
        lexemes.add(new LexemeBean(LexemeType.END_OF_EQUATION, EMPTY_PLACE));
        return lexemes;
    }
}

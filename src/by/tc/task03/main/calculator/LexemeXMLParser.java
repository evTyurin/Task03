package by.tc.task03.main.calculator;

import java.util.ArrayList;
import java.util.List;

public class LexemeXMLParser {

    public static List<LexemeBean> lexemes;

    LexemeXMLParser(String equation) {
        this.lexemes = lexAnalyze(equation);
    }

    public List getManyLexemas() {
        return lexemes;
    }

    public List<LexemeBean> lexAnalyze(String expText) {
        ArrayList<LexemeBean> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos< expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new LexemeBean(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new LexemeBean(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new LexemeBean(LexemeType.PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new LexemeBean(LexemeType.MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new LexemeBean(LexemeType.MULTIPLICATION, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new LexemeBean(LexemeType.DIVISION, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new LexemeBean(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new RuntimeException("Unexpected character: " + c);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new LexemeBean(LexemeType.END_OF_EQUATION, ""));
        return lexemes;
    }
}

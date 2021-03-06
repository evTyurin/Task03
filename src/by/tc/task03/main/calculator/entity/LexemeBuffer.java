package by.tc.task03.main.calculator.entity;

import java.util.List;

public class LexemeBuffer  {

    private int position;
    private List<LexemeBean> lexemes;

    public LexemeBuffer(List<LexemeBean> lexemes) {
        this.lexemes = lexemes;
    }

    public LexemeBean next() {
        return lexemes.get(position++);
    }

    public void back() {
        position--;
    }

    public int getPosition() {
        return position;
    }
}

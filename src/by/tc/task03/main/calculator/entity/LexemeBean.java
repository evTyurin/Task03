package by.tc.task03.main.calculator.entity;

import java.io.Serializable;

public class LexemeBean implements Serializable {
    private LexemeType type;
    private String value;

    public LexemeBean() {};

    public LexemeBean(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LexemeBean(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public LexemeType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LexemeBean{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
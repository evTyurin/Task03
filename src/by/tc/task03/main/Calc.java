package by.tc.task03.main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calc {
    private static Calc instance;

    private Calc() {
    }

    public static Calc getInstance()  {
        if (instance == null) {
            instance = new Calc();
        }
        return instance;
    }

    public String getCalc (String expression) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(expression);
            return result.toString();
        } catch (ScriptException e) {
            System.err.println("Error evaluating the script: " + e.getMessage());
        }
        return null;
    }



}

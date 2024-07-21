package org.codehaus.janino;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.commons.compiler.jdk.ScriptEvaluator;
import org.codehaus.commons.compiler.samples.ScriptDemo;

import java.lang.reflect.InvocationTargetException;

public class ScriptEvaluation {
    public static void main(String[] args) throws Exception {
        ScriptEvaluator se = new ScriptEvaluator();

        se.cook(
                "static void method1() {\n"
                        + "System.out.println(1);\n"
                        + "}\n"
                        + "\n"
                        + "method1();\n"
                        + "method2();\n"
                        + "\n"
                        + "static void method2() {\n"
                        + "    System.out.println(2);\n"
                        + "}\n"
        );

        se.evaluate();
    }
}

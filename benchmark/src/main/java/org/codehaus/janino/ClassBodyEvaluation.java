package org.codehaus.janino;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IClassBodyEvaluator;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassBodyEvaluation {
    public static void main(String[] args) throws Exception {

        IClassBodyEvaluator cbe = (
                CompilerFactoryFactory
                        .getDefaultCompilerFactory(ClassBodyEvaluation.class.getClassLoader())
                        .newClassBodyEvaluator()
        );
        String classBody = "public static void\n" +
                "main(String[] args) {\n" +
                "    System.out.println(java.util.Arrays.asList(args));\n" +
                "}";
        String[] arguments = {"a", "b", "c"};
        cbe.cook(classBody);
        Class<?> c = cbe.getClazz();

        // Invoke the "public static main(String[])" method.
        Method m           = c.getMethod("main", String[].class);
        Object returnValue = m.invoke(null, (Object) arguments);

        // If non-VOID, print the return value.
        if (m.getReturnType() != void.class) {
            System.out.println(
                    returnValue instanceof Object[]
                            ? Arrays.toString((Object[]) returnValue)
                            : String.valueOf(returnValue)
            );
        }
    }
}

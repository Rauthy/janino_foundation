package org.codehaus.janino;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.samples.CompilerDemo;

import java.io.File;
import java.nio.charset.Charset;

public class FileCompile {
    public static void main(String[] args) throws Exception {
        File destinationDirectory  = ICompiler.NO_DESTINATION_DIRECTORY;
        File[]          sourcePath            = new File[0];
        File[]          classPath             = { new File(".") };
        File[]          extDirs               = new File[0];
        File[]          bootClassPath         = null;
        Charset encoding              = Charset.defaultCharset();
        boolean         verbose               = false;
        boolean         debugSource           = true;
        boolean         debugLines            = true;
        boolean         debugVars             = false;
        boolean         rebuild               = false;

        File[] sourceFiles = new File[1];
        sourceFiles[0] = new File("/home/rauthy/project/janino/benchmark/src/main/resources/script/ModuleA.java");
        // Create the compiler object.
        ICompiler compiler = (
                CompilerFactoryFactory
                        .getDefaultCompilerFactory(FileCompile.class.getClassLoader())
                        .newCompiler()
        );
        compiler.setSourcePath(sourcePath);
        compiler.setClassPath(classPath);
        compiler.setExtensionDirectories(extDirs);
        if (bootClassPath != null) compiler.setBootClassPath(bootClassPath);
        compiler.setDestinationDirectory(destinationDirectory, rebuild);
        compiler.setEncoding(encoding);
        compiler.setVerbose(verbose);
        compiler.setDebugSource(debugSource);
        compiler.setDebugLines(debugLines);
        compiler.setDebugVars(debugVars);

        // Compile source files.
        try {
            compiler.compile(sourceFiles);
        } catch (Exception e) {

            if (verbose) {
                e.printStackTrace();
            } else {
                System.err.println(e.toString());
            }

            System.exit(1);
        }

    }
}

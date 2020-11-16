package com.mzdora;

import com.mzdora.bytecodegeneration.classscopeinstructions.CompilationUnit;
import com.mzdora.parsing.TWL.SyntaxTreeTraverser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Compiler {

    public static void main(String[] args) throws Exception {
        new Compiler().compile(args);
    }

    public void compile(String[] args) throws Exception {
        //arguments validation skipped (check out full code on github)
        final File enkelFile = new File(args[0]);
        String fileAbsolutePath = enkelFile.getAbsolutePath();
        final CompilationUnit compilationUnit = new SyntaxTreeTraverser().getCompilationUnit(fileAbsolutePath);
        saveBytecodeToClassFile(compilationUnit);

    }

    private static void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        final byte[] byteCode = compilationUnit.getByteCode();
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        OutputStream os = new FileOutputStream(fileName);
        IOUtils.write(byteCode,os);

    }
}
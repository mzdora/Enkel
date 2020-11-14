package com.mzdora;

import com.mzdora.bytecodegeneration.classscopeinstructions.BytecodeGenerator;
import com.mzdora.bytecodegeneration.classscopeinstructions.Instruction;
import com.mzdora.parsing.TWL.SyntaxTreeTraverser;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

public class Compiler {

    public static void main(String[] args) throws Exception {
        new Compiler().compile(args);
    }

    public void compile(String[] args) throws Exception {
        //arguments validation skipped (check out full code on github)
        final File enkelFile = new File(args[0]);
        String fileName = enkelFile.getName();
        String fileAbsolutePath = enkelFile.getAbsolutePath();
        String className = StringUtils.remove(fileName, ".enk");
        final Queue<Instruction> instructionsQueue = new SyntaxTreeTraverser().getInstructions(fileAbsolutePath);
        final byte[] byteCode = new BytecodeGenerator().generateBytecode(instructionsQueue, className);
        saveBytecodeToClassFile(fileName,byteCode);
    }

    private static void saveBytecodeToClassFile(String fileName, byte[] byteCode) throws IOException {
        final String classFile = StringUtils.replace(fileName, ".enk", ".class");
        OutputStream os = new FileOutputStream(classFile);
        os.write(byteCode);
        os.close();
    }
}
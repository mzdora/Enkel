package com.mzdora.parsing.TWL;

import com.mzdora.bytecodegeneration.classscopeinstructions.Instruction;
import com.mzdora.parsing.EnkelLexer;
import com.mzdora.parsing.EnkelParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.Queue;

public class SyntaxTreeTraverser {
    public Queue<Instruction> getInstructions(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath); //fileAbsolutePath - file containing first enk code file

        EnkelLexer lexer = new EnkelLexer(charStream);  //create lexer (pass enk file to it)

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        EnkelParser parser = new EnkelParser(tokenStream);

        EnkelTreeWalkListener listener = new EnkelTreeWalkListener(); //EnkelTreeWalkListener extends EnkelBaseLitener - handles parse tree visiting events
        BaseErrorListener errorListener = new EnkelTreeWalkErrorListener(); //EnkelTreeWalkErrorListener - handles parse tree visiting error events

        parser.addErrorListener(errorListener);
        parser.addParseListener(listener);
        parser.compilationUnit(); //compilation unit is root parser rule - start from it!
        return listener.getInstructionsQueue();
    }
}
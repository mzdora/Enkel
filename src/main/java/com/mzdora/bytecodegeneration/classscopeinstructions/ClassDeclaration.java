package com.mzdora.bytecodegeneration.classscopeinstructions;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClassDeclaration {
    private Queue<Instruction> Instructions = new ArrayDeque<>();
    private String name;


    public ClassDeclaration(Queue<Instruction> classScopeInstructions, String name) {
        this.Instructions = classScopeInstructions;
        this.name = name;
    }

    public void add(Instruction instruction) {
        this.Instructions.add(instruction);
    }


    public Queue<Instruction> getClassScopeInstructions() {
        return Instructions;
    }

    public String getName() {
        return name;
    }
}
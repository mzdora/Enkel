package com.mzdora.bytecodegeneration.classscopeinstructions;

import org.objectweb.asm.MethodVisitor;

public interface Instruction {
    void apply(MethodVisitor mv);
}

package com.mzdora.bytecodegeneration.classscopeinstructions;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class CompilationUnit {
    private ClassDeclaration classDeclaration;

    public CompilationUnit(ClassDeclaration classDeclaration) {
        this.classDeclaration = classDeclaration;
    }

    public ClassDeclaration getClassDeclaration() {
        return classDeclaration;
    }

    public String getClassName() {
        return classDeclaration.getName();
    }

    public byte[] getByteCode() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(52, ACC_PUBLIC + ACC_SUPER, getClassName(), null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        {
            final long localVariablesCount = classDeclaration.getClassScopeInstructions().stream()
                    .filter(instruction -> instruction instanceof VariableDeclaration)
                    .count();
            final int maxStack = 100;
            for (Instruction classScopeInstruction : classDeclaration.getClassScopeInstructions()) {
                classScopeInstruction.apply(mv);
            }
            mv.visitInsn(RETURN);
            mv.visitMaxs(maxStack, (int) localVariablesCount);
            mv.visitEnd();
        }
        cw.visitEnd();
        return cw.toByteArray();
    }
}
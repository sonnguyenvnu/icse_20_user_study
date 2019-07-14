/** 
 * Visits return opcodes.
 */
public static void visitReturn(final MethodVisitor mv,final MethodInfo methodInfo,final boolean isLast){
switch (methodInfo.getReturnType().getOpcode()) {
case 'V':
    if (isLast) {
      mv.visitInsn(POP);
    }
  mv.visitInsn(RETURN);
break;
case 'B':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(ICONST_0);
mv.visitInsn(IRETURN);
mv.visitLabel(label);
AsmUtil.byteValue(mv);
}
mv.visitInsn(IRETURN);
break;
case 'C':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(ICONST_0);
mv.visitInsn(IRETURN);
mv.visitLabel(label);
AsmUtil.charValue(mv);
}
mv.visitInsn(IRETURN);
break;
case 'S':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(ICONST_0);
mv.visitInsn(IRETURN);
mv.visitLabel(label);
AsmUtil.shortValue(mv);
}
mv.visitInsn(IRETURN);
break;
case 'I':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(ICONST_0);
mv.visitInsn(IRETURN);
mv.visitLabel(label);
AsmUtil.intValue(mv);
}
mv.visitInsn(IRETURN);
break;
case 'Z':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(ICONST_0);
mv.visitInsn(IRETURN);
mv.visitLabel(label);
AsmUtil.booleanValue(mv);
}
mv.visitInsn(IRETURN);
break;
case 'J':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(LCONST_0);
mv.visitInsn(LRETURN);
mv.visitLabel(label);
AsmUtil.longValue(mv);
}
mv.visitInsn(LRETURN);
break;
case 'F':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(FCONST_0);
mv.visitInsn(FRETURN);
mv.visitLabel(label);
AsmUtil.floatValue(mv);
}
mv.visitInsn(FRETURN);
break;
case 'D':
if (isLast) {
mv.visitInsn(DUP);
Label label=new Label();
mv.visitJumpInsn(IFNONNULL,label);
mv.visitInsn(POP);
mv.visitInsn(DCONST_0);
mv.visitInsn(DRETURN);
mv.visitLabel(label);
AsmUtil.doubleValue(mv);
}
mv.visitInsn(DRETURN);
break;
default :
mv.visitInsn(ARETURN);
break;
}
}

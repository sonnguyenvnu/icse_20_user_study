private void _isFlag(MethodVisitor mw,Context context,int i,Label label){
  mw.visitVarInsn(ILOAD,context.var("_asm_flag_" + (i / 32)));
  mw.visitLdcInsn(1 << i);
  mw.visitInsn(IAND);
  mw.visitJumpInsn(IFEQ,label);
}

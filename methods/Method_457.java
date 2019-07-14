private void _setFlag(MethodVisitor mw,Context context,int i){
  String varName="_asm_flag_" + (i / 32);
  mw.visitVarInsn(ILOAD,context.var(varName));
  mw.visitLdcInsn(1 << i);
  mw.visitInsn(IOR);
  mw.visitVarInsn(ISTORE,context.var(varName));
}

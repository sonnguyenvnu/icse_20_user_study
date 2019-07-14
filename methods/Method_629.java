private void _before(MethodVisitor mw,Context context){
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,Context.serializer);
  mw.visitVarInsn(ALOAD,Context.obj);
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,"writeBefore","(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
  mw.visitVarInsn(ISTORE,context.var("seperator"));
}

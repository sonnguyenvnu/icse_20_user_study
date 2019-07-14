private void _after(MethodVisitor mw,Context context){
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,Context.serializer);
  mw.visitVarInsn(ALOAD,2);
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,"writeAfter","(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
  mw.visitVarInsn(ISTORE,context.var("seperator"));
}

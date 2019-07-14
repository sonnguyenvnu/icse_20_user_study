private void _int(Class<?> clazz,MethodVisitor mw,FieldInfo property,Context context,int var,char type){
  Label end_=new Label();
  _nameApply(mw,property,context,end_);
  _get(mw,context,property);
  mw.visitVarInsn(ISTORE,var);
  _filters(mw,property,context,end_);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitVarInsn(ALOAD,Context.fieldName);
  mw.visitVarInsn(ILOAD,var);
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"writeFieldValue","(CLjava/lang/String;" + type + ")V");
  _seperator(mw,context);
  mw.visitLabel(end_);
}

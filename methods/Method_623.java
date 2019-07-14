private void _object(Class<?> clazz,MethodVisitor mw,FieldInfo property,Context context){
  Label _end=new Label();
  _nameApply(mw,property,context,_end);
  _get(mw,context,property);
  mw.visitVarInsn(ASTORE,context.var("object"));
  _filters(mw,property,context,_end);
  _writeObject(mw,property,context,_end);
  mw.visitLabel(_end);
}

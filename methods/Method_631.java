private void _notWriteDefault(MethodVisitor mw,FieldInfo property,Context context,Label _end){
  if (context.writeDirect) {
    return;
  }
  Label elseLabel=new Label();
  mw.visitVarInsn(ILOAD,context.var("notWriteDefaultValue"));
  mw.visitJumpInsn(IFEQ,elseLabel);
  Class<?> propertyClass=property.fieldClass;
  if (propertyClass == boolean.class) {
    mw.visitVarInsn(ILOAD,context.var("boolean"));
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == byte.class) {
    mw.visitVarInsn(ILOAD,context.var("byte"));
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == short.class) {
    mw.visitVarInsn(ILOAD,context.var("short"));
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == int.class) {
    mw.visitVarInsn(ILOAD,context.var("int"));
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == long.class) {
    mw.visitVarInsn(LLOAD,context.var("long"));
    mw.visitInsn(LCONST_0);
    mw.visitInsn(LCMP);
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == float.class) {
    mw.visitVarInsn(FLOAD,context.var("float"));
    mw.visitInsn(FCONST_0);
    mw.visitInsn(FCMPL);
    mw.visitJumpInsn(IFEQ,_end);
  }
 else   if (propertyClass == double.class) {
    mw.visitVarInsn(DLOAD,context.var("double"));
    mw.visitInsn(DCONST_0);
    mw.visitInsn(DCMPL);
    mw.visitJumpInsn(IFEQ,_end);
  }
  mw.visitLabel(elseLabel);
}

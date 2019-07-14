private void _processValue(MethodVisitor mw,FieldInfo fieldInfo,Context context,Label _end){
  Label processKeyElse_=new Label();
  Class<?> fieldClass=fieldInfo.fieldClass;
  if (fieldClass.isPrimitive()) {
    Label checkValueEnd_=new Label();
    mw.visitVarInsn(ILOAD,context.var("checkValue"));
    mw.visitJumpInsn(IFNE,checkValueEnd_);
    mw.visitInsn(ACONST_NULL);
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ASTORE,Context.processValue);
    mw.visitJumpInsn(GOTO,processKeyElse_);
    mw.visitLabel(checkValueEnd_);
  }
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,Context.serializer);
  mw.visitVarInsn(ALOAD,0);
  mw.visitLdcInsn(context.getFieldOrinal(fieldInfo.name));
  mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,"getBeanContext","(I)" + desc(BeanContext.class));
  mw.visitVarInsn(ALOAD,Context.obj);
  mw.visitVarInsn(ALOAD,Context.fieldName);
  String valueDesc="Ljava/lang/Object;";
  if (fieldClass == byte.class) {
    mw.visitVarInsn(ILOAD,context.var("byte"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Byte","valueOf","(B)Ljava/lang/Byte;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == short.class) {
    mw.visitVarInsn(ILOAD,context.var("short"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Short","valueOf","(S)Ljava/lang/Short;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == int.class) {
    mw.visitVarInsn(ILOAD,context.var("int"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == char.class) {
    mw.visitVarInsn(ILOAD,context.var("char"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Character","valueOf","(C)Ljava/lang/Character;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == long.class) {
    mw.visitVarInsn(LLOAD,context.var("long",2));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Long","valueOf","(J)Ljava/lang/Long;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == float.class) {
    mw.visitVarInsn(FLOAD,context.var("float"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Float","valueOf","(F)Ljava/lang/Float;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == double.class) {
    mw.visitVarInsn(DLOAD,context.var("double",2));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Double","valueOf","(D)Ljava/lang/Double;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == boolean.class) {
    mw.visitVarInsn(ILOAD,context.var("boolean"));
    mw.visitMethodInsn(INVOKESTATIC,"java/lang/Boolean","valueOf","(Z)Ljava/lang/Boolean;");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ASTORE,Context.original);
  }
 else   if (fieldClass == BigDecimal.class) {
    mw.visitVarInsn(ALOAD,context.var("decimal"));
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ALOAD,Context.original);
  }
 else   if (fieldClass == String.class) {
    mw.visitVarInsn(ALOAD,context.var("string"));
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ALOAD,Context.original);
  }
 else   if (fieldClass.isEnum()) {
    mw.visitVarInsn(ALOAD,context.var("enum"));
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ALOAD,Context.original);
  }
 else   if (List.class.isAssignableFrom(fieldClass)) {
    mw.visitVarInsn(ALOAD,context.var("list"));
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ALOAD,Context.original);
  }
 else {
    mw.visitVarInsn(ALOAD,context.var("object"));
    mw.visitVarInsn(ASTORE,Context.original);
    mw.visitVarInsn(ALOAD,Context.original);
  }
  mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,"processValue","(L" + JSONSerializer + ";" + desc(BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;" + valueDesc + ")Ljava/lang/Object;");
  mw.visitVarInsn(ASTORE,Context.processValue);
  mw.visitVarInsn(ALOAD,Context.original);
  mw.visitVarInsn(ALOAD,Context.processValue);
  mw.visitJumpInsn(IF_ACMPEQ,processKeyElse_);
  _writeObject(mw,fieldInfo,context,_end);
  mw.visitJumpInsn(GOTO,_end);
  mw.visitLabel(processKeyElse_);
}

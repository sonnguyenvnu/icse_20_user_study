private void _loadAndSet(Context context,MethodVisitor mw,FieldInfo fieldInfo){
  Class<?> fieldClass=fieldInfo.fieldClass;
  Type fieldType=fieldInfo.fieldType;
  if (fieldClass == boolean.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ILOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
 else   if (fieldClass == byte.class || fieldClass == short.class || fieldClass == int.class || fieldClass == char.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ILOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
 else   if (fieldClass == long.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(LLOAD,context.var(fieldInfo.name + "_asm",2));
    if (fieldInfo.method != null) {
      mw.visitMethodInsn(INVOKEVIRTUAL,type(context.getInstClass()),fieldInfo.method.getName(),desc(fieldInfo.method));
      if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
        mw.visitInsn(POP);
      }
    }
 else {
      mw.visitFieldInsn(PUTFIELD,type(fieldInfo.declaringClass),fieldInfo.field.getName(),desc(fieldInfo.fieldClass));
    }
  }
 else   if (fieldClass == float.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(FLOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
 else   if (fieldClass == double.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(DLOAD,context.var(fieldInfo.name + "_asm",2));
    _set(context,mw,fieldInfo);
  }
 else   if (fieldClass == String.class) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
 else   if (fieldClass.isEnum()) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
 else   if (Collection.class.isAssignableFrom(fieldClass)) {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    Type itemType=TypeUtils.getCollectionItemClass(fieldType);
    if (itemType == String.class) {
      mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
      mw.visitTypeInsn(CHECKCAST,type(fieldClass));
    }
 else {
      mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
    }
    _set(context,mw,fieldInfo);
  }
 else {
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
    _set(context,mw,fieldInfo);
  }
}

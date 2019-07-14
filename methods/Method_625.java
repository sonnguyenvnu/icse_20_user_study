private void _get(MethodVisitor mw,Context context,FieldInfo fieldInfo){
  Method method=fieldInfo.method;
  if (method != null) {
    mw.visitVarInsn(ALOAD,context.var("entity"));
    Class<?> declaringClass=method.getDeclaringClass();
    mw.visitMethodInsn(declaringClass.isInterface() ? INVOKEINTERFACE : INVOKEVIRTUAL,type(declaringClass),method.getName(),desc(method));
    if (!method.getReturnType().equals(fieldInfo.fieldClass)) {
      mw.visitTypeInsn(CHECKCAST,type(fieldInfo.fieldClass));
    }
  }
 else {
    mw.visitVarInsn(ALOAD,context.var("entity"));
    Field field=fieldInfo.field;
    mw.visitFieldInsn(GETFIELD,type(fieldInfo.declaringClass),field.getName(),desc(field.getType()));
    if (!field.getType().equals(fieldInfo.fieldClass)) {
      mw.visitTypeInsn(CHECKCAST,type(fieldInfo.fieldClass));
    }
  }
}

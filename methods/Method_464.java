private void _set(Context context,MethodVisitor mw,FieldInfo fieldInfo){
  Method method=fieldInfo.method;
  if (method != null) {
    Class<?> declaringClass=method.getDeclaringClass();
    mw.visitMethodInsn(declaringClass.isInterface() ? INVOKEINTERFACE : INVOKEVIRTUAL,type(fieldInfo.declaringClass),method.getName(),desc(method));
    if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
      mw.visitInsn(POP);
    }
  }
 else {
    mw.visitFieldInsn(PUTFIELD,type(fieldInfo.declaringClass),fieldInfo.field.getName(),desc(fieldInfo.fieldClass));
  }
}

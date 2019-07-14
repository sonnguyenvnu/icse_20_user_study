private void _createInstance(ClassWriter cw,Context context){
  Constructor<?> defaultConstructor=context.beanInfo.defaultConstructor;
  if (!Modifier.isPublic(defaultConstructor.getModifiers())) {
    return;
  }
  MethodVisitor mw=new MethodWriter(cw,ACC_PUBLIC,"createInstance","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;",null,null);
  mw.visitTypeInsn(NEW,type(context.getInstClass()));
  mw.visitInsn(DUP);
  mw.visitMethodInsn(INVOKESPECIAL,type(context.getInstClass()),"<init>","()V");
  mw.visitInsn(ARETURN);
  mw.visitMaxs(3,3);
  mw.visitEnd();
}

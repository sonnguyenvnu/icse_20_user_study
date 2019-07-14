private void _createInstance(Context context,MethodVisitor mw){
  JavaBeanInfo beanInfo=context.beanInfo;
  Constructor<?> defaultConstructor=beanInfo.defaultConstructor;
  if (Modifier.isPublic(defaultConstructor.getModifiers())) {
    mw.visitTypeInsn(NEW,type(context.getInstClass()));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(defaultConstructor.getDeclaringClass()),"<init>","()V");
    mw.visitVarInsn(ASTORE,context.var("instance"));
  }
 else {
    mw.visitVarInsn(ALOAD,0);
    mw.visitVarInsn(ALOAD,1);
    mw.visitVarInsn(ALOAD,0);
    mw.visitFieldInsn(GETFIELD,type(JavaBeanDeserializer.class),"clazz","Ljava/lang/Class;");
    mw.visitMethodInsn(INVOKESPECIAL,type(JavaBeanDeserializer.class),"createInstance","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;");
    mw.visitTypeInsn(CHECKCAST,type(context.getInstClass()));
    mw.visitVarInsn(ASTORE,context.var("instance"));
  }
}

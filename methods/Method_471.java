private void _newCollection(MethodVisitor mw,Class<?> fieldClass,int i,boolean set){
  if (fieldClass.isAssignableFrom(ArrayList.class) && !set) {
    mw.visitTypeInsn(NEW,"java/util/ArrayList");
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,"java/util/ArrayList","<init>","()V");
  }
 else   if (fieldClass.isAssignableFrom(LinkedList.class) && !set) {
    mw.visitTypeInsn(NEW,type(LinkedList.class));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(LinkedList.class),"<init>","()V");
  }
 else   if (fieldClass.isAssignableFrom(HashSet.class)) {
    mw.visitTypeInsn(NEW,type(HashSet.class));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(HashSet.class),"<init>","()V");
  }
 else   if (fieldClass.isAssignableFrom(TreeSet.class)) {
    mw.visitTypeInsn(NEW,type(TreeSet.class));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(TreeSet.class),"<init>","()V");
  }
 else   if (fieldClass.isAssignableFrom(LinkedHashSet.class)) {
    mw.visitTypeInsn(NEW,type(LinkedHashSet.class));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(LinkedHashSet.class),"<init>","()V");
  }
 else   if (set) {
    mw.visitTypeInsn(NEW,type(HashSet.class));
    mw.visitInsn(DUP);
    mw.visitMethodInsn(INVOKESPECIAL,type(HashSet.class),"<init>","()V");
  }
 else {
    mw.visitVarInsn(ALOAD,0);
    mw.visitLdcInsn(i);
    mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"getFieldType","(I)Ljava/lang/reflect/Type;");
    mw.visitMethodInsn(INVOKESTATIC,type(TypeUtils.class),"createCollection","(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
  }
  mw.visitTypeInsn(CHECKCAST,type(fieldClass));
}

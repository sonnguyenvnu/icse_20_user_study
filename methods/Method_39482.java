public static void valueOfInteger(final MethodVisitor mv){
  mv.visitMethodInsn(INVOKESTATIC,SIGNATURE_JAVA_LANG_INTEGER,"valueOf","(I)Ljava/lang/Integer;",false);
}

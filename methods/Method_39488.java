public static void valueOfBoolean(final MethodVisitor mv){
  mv.visitMethodInsn(INVOKESTATIC,SIGNATURE_JAVA_LANG_BOOLEAN,"valueOf","(Z)Ljava/lang/Boolean;",false);
}

public static void valueOfDouble(final MethodVisitor mv){
  mv.visitMethodInsn(INVOKESTATIC,SIGNATURE_JAVA_LANG_DOUBLE,"valueOf","(D)Ljava/lang/Double;",false);
}

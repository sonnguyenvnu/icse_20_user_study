public static void valueOfFloat(final MethodVisitor mv){
  mv.visitMethodInsn(INVOKESTATIC,SIGNATURE_JAVA_LANG_FLOAT,"valueOf","(F)Ljava/lang/Float;",false);
}

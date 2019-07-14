/** 
 * Visits replacement code for  {@link ProxyTarget#createArgumentsClassArray()}.
 */
public static void createArgumentsClassArray(final MethodVisitor mv,final MethodInfo methodInfo){
  int argsCount=methodInfo.getArgumentsCount();
  pushInt(mv,argsCount);
  mv.visitTypeInsn(ANEWARRAY,AsmUtil.SIGNATURE_JAVA_LANG_CLASS);
  for (int i=0; i < argsCount; i++) {
    mv.visitInsn(DUP);
    pushInt(mv,i);
    loadMethodArgumentClass(mv,methodInfo,i + 1);
    mv.visitInsn(AASTORE);
  }
}

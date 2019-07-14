/** 
 * Visits replacement code for  {@link ProxyTarget#createArgumentsArray()}.
 */
public static void createArgumentsArray(final MethodVisitor mv,final MethodInfo methodInfo){
  int argsCount=methodInfo.getArgumentsCount();
  pushInt(mv,argsCount);
  mv.visitTypeInsn(ANEWARRAY,AsmUtil.SIGNATURE_JAVA_LANG_OBJECT);
  for (int i=0; i < argsCount; i++) {
    mv.visitInsn(DUP);
    pushInt(mv,i);
    loadMethodArgumentAsObject(mv,methodInfo,i + 1);
    mv.visitInsn(AASTORE);
  }
}

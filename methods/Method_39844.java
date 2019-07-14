/** 
 * Visits replacement code for  {@link ProxyTarget#argumentType(int)}.
 */
public static void argumentType(final MethodVisitor mv,final MethodInfo methodInfo,final int argIndex){
  checkArgumentIndex(methodInfo,argIndex);
  mv.visitInsn(POP);
  loadMethodArgumentClass(mv,methodInfo,argIndex);
}

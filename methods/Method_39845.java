/** 
 * Visits replacement code for  {@link ProxyTarget#argument(int)}.
 */
public static void argument(final MethodVisitor mv,final MethodInfo methodInfo,final int argIndex){
  checkArgumentIndex(methodInfo,argIndex);
  mv.visitInsn(POP);
  loadMethodArgumentAsObject(mv,methodInfo,argIndex);
}

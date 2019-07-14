/** 
 * Visits replacement code for  {@link ProxyTarget#argumentsCount()}.
 */
public static void argumentsCount(final MethodVisitor mv,final MethodInfo methodInfo){
  int argsCount=methodInfo.getArgumentsCount();
  pushInt(mv,argsCount);
}

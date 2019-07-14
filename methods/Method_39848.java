/** 
 * Visits replacement code for  {@link ProxyTarget#returnType()}.
 */
public static void returnType(final MethodVisitor mv,final MethodInfo methodInfo){
  ProxettaAsmUtil.loadClass(mv,methodInfo.getReturnType().getOpcode(),methodInfo.getReturnType().getName());
}

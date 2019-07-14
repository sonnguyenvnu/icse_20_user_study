/** 
 * Unsafe version of:  {@link #class_replaceMethod} 
 */
public static long nclass_replaceMethod(long cls,long name,long imp,long types){
  long __functionAddress=Functions.class_replaceMethod;
  if (CHECKS) {
    check(cls);
    check(name);
    check(imp);
  }
  return invokePPPPP(cls,name,imp,types,__functionAddress);
}

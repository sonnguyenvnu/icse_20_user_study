/** 
 * Unsafe version of:  {@link #class_getInstanceVariable} 
 */
public static long nclass_getInstanceVariable(long cls,long name){
  long __functionAddress=Functions.class_getInstanceVariable;
  if (CHECKS) {
    check(cls);
  }
  return invokePPP(cls,name,__functionAddress);
}

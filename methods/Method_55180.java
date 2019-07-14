/** 
 * Unsafe version of:  {@link #class_getClassVariable} 
 */
public static long nclass_getClassVariable(long cls,long name){
  long __functionAddress=Functions.class_getClassVariable;
  if (CHECKS) {
    check(cls);
  }
  return invokePPP(cls,name,__functionAddress);
}

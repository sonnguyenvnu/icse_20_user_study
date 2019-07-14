/** 
 * Unsafe version of:  {@link #class_getImageName} 
 */
public static long nclass_getImageName(long cls){
  long __functionAddress=Functions.class_getImageName;
  if (CHECKS) {
    check(cls);
  }
  return invokePP(cls,__functionAddress);
}

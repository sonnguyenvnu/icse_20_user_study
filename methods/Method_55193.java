/** 
 * Unsafe version of:  {@link #class_getIvarLayout} 
 */
public static long nclass_getIvarLayout(long cls){
  long __functionAddress=Functions.class_getIvarLayout;
  if (CHECKS) {
    check(cls);
  }
  return invokePP(cls,__functionAddress);
}

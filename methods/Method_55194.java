/** 
 * Unsafe version of:  {@link #class_getWeakIvarLayout} 
 */
public static long nclass_getWeakIvarLayout(long cls){
  long __functionAddress=Functions.class_getWeakIvarLayout;
  if (CHECKS) {
    check(cls);
  }
  return invokePP(cls,__functionAddress);
}

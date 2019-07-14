/** 
 * Unsafe version of:  {@link #class_setIvarLayout} 
 */
public static void nclass_setIvarLayout(long cls,long layout){
  long __functionAddress=Functions.class_setIvarLayout;
  if (CHECKS) {
    check(cls);
  }
  invokePPV(cls,layout,__functionAddress);
}

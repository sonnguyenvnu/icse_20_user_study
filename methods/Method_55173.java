/** 
 * Unsafe version of:  {@link #class_getName} 
 */
public static long nclass_getName(long cls){
  long __functionAddress=Functions.class_getName;
  return invokePP(cls,__functionAddress);
}

/** 
 * Unsafe version of:  {@link #class_getProperty} 
 */
public static long nclass_getProperty(long cls,long name){
  long __functionAddress=Functions.class_getProperty;
  return invokePPP(cls,name,__functionAddress);
}

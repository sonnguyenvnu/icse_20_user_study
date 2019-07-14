/** 
 * Unsafe version of:  {@link #class_copyMethodList}
 * @param outCount on return, contains the length of the returned array. If {@code outCount} is {@code NULL}, the length is not returned
 */
public static long nclass_copyMethodList(long cls,long outCount){
  long __functionAddress=Functions.class_copyMethodList;
  return invokePPP(cls,outCount,__functionAddress);
}

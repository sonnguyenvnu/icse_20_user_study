/** 
 * Unsafe version of:  {@link #class_copyPropertyList}
 * @param outCount on return, contains the length of the returned array. If {@code outCount} is {@code NULL}, the length is not returned
 */
public static long nclass_copyPropertyList(long cls,long outCount){
  long __functionAddress=Functions.class_copyPropertyList;
  return invokePPP(cls,outCount,__functionAddress);
}

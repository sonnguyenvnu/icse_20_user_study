/** 
 * Unsafe version of:  {@link #objc_getClassList}
 * @param bufferCount the number of pointers for which you have allocated space in buffer. On return, this function fills in only this number of elements. If this numberis less than the number of registered classes, this function returns an arbitrary subset of the registered classes.
 */
public static int nobjc_getClassList(long buffer,int bufferCount){
  long __functionAddress=Functions.objc_getClassList;
  return invokePI(buffer,bufferCount,__functionAddress);
}

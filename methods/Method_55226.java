/** 
 * Unsafe version of:  {@link #objc_copyProtocolList}
 * @param outCount upon return, contains the number of protocols in the returned array
 */
public static long nobjc_copyProtocolList(long outCount){
  long __functionAddress=Functions.objc_copyProtocolList;
  return invokePP(outCount,__functionAddress);
}

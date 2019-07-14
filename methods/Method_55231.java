/** 
 * Unsafe version of:  {@link #protocol_getProperty} 
 */
public static long nprotocol_getProperty(long proto,long name,boolean isRequiredProperty,boolean isInstanceProperty){
  long __functionAddress=Functions.protocol_getProperty;
  if (CHECKS) {
    check(proto);
  }
  return invokePPP(proto,name,isRequiredProperty,isInstanceProperty,__functionAddress);
}

/** 
 * Unsafe version of:  {@link #protocol_getMethodDescription} 
 */
public static void nprotocol_getMethodDescription(long p,long aSel,boolean isRequiredMethod,boolean isInstanceMethod,long __result){
  long __functionAddress=Functions.protocol_getMethodDescription;
  if (CHECKS) {
    check(p);
    check(aSel);
  }
  nprotocol_getMethodDescription(p,aSel,isRequiredMethod,isInstanceMethod,__functionAddress,__result);
}

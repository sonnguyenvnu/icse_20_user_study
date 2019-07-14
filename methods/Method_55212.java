/** 
 * Unsafe version of:  {@link #method_getReturnType}
 * @param dst_len the maximum number of characters that can be stored in {@code dst}
 */
public static void nmethod_getReturnType(long m,long dst,long dst_len){
  long __functionAddress=Functions.method_getReturnType;
  if (CHECKS) {
    check(m);
  }
  invokePPPV(m,dst,dst_len,__functionAddress);
}

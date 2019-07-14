/** 
 * Unsafe version of:  {@link #method_getArgumentType}
 * @param dst_len the maximum number of characters that can be stored in {@code dst}
 */
public static void nmethod_getArgumentType(long m,int index,long dst,long dst_len){
  long __functionAddress=Functions.method_getArgumentType;
  if (CHECKS) {
    check(m);
  }
  invokePPPV(m,index,dst,dst_len,__functionAddress);
}

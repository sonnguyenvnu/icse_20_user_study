/** 
 * Unsafe version of  {@link #srcContext(long) srcContext}. 
 */
public static void nsrcContext(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCCONTEXT,check(value));
}

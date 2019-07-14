/** 
 * Unsafe version of  {@link #params_reserved(IntBuffer) params_reserved}. 
 */
public static void nparams_reserved(long struct,IntBuffer value){
  if (CHECKS) {
    checkGT(value,16);
  }
  memCopy(memAddress(value),struct + CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS.PARAMS_RESERVED,value.remaining() * 4);
}

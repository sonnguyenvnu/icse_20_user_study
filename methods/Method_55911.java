/** 
 * Unsafe version of  {@link #params_reserved}. 
 */
public static IntBuffer nparams_reserved(long struct){
  return memIntBuffer(struct + CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS.PARAMS_RESERVED,16);
}

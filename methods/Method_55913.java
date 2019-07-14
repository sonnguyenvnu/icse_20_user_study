/** 
 * Unsafe version of  {@link #params_fence_value(long) params_fence_value}. 
 */
public static void nparams_fence_value(long struct,long value){
  UNSAFE.putLong(null,struct + CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS.PARAMS_FENCE_VALUE,value);
}

/** 
 * Sets the specified value to the  {@code params.fence.value} field. 
 */
public CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS params_fence_value(@NativeType("unsigned long long") long value){
  nparams_fence_value(address(),value);
  return this;
}

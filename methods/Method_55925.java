/** 
 * Sets the specified value to the  {@code gridDimZ} field. 
 */
public CUDA_KERNEL_NODE_PARAMS gridDimZ(@NativeType("unsigned int") int value){
  ngridDimZ(address(),value);
  return this;
}

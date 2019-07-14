/** 
 * Sets the address of the specified  {@link PointerBuffer} to the {@code kernelParams} field. 
 */
public CUDA_KERNEL_NODE_PARAMS kernelParams(@NativeType("void **") PointerBuffer value){
  nkernelParams(address(),value);
  return this;
}

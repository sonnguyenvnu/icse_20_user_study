/** 
 * Sets the address of the specified  {@link ByteBuffer} to the {@code reserved1} field. 
 */
public CUDA_MEMCPY3D reserved1(@NativeType("void *") ByteBuffer value){
  nreserved1(address(),value);
  return this;
}

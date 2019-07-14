/** 
 * Returns the value of the  {@code numGPUs} field. 
 */
@NativeType("uint8_t") public byte numGPUs(){
  return nnumGPUs(address());
}

/** 
 * Returns the value of the  {@code backBufferDS} field. 
 */
@NativeType("void *") public long backBufferDS(){
  return nbackBufferDS(address());
}

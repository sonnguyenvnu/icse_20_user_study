/** 
 * Returns the value of the  {@code backBuffer} field. 
 */
@NativeType("void *") public long backBuffer(){
  return nbackBuffer(address());
}

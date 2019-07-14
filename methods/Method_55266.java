/** 
 * Returns the value of the  {@code dmBitsPerPel} field. 
 */
@NativeType("DWORD") public int dmBitsPerPel(){
  return ndmBitsPerPel(address());
}

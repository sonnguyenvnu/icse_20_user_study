/** 
 * Returns the value at the specified index of the  {@code pad} field. 
 */
@NativeType("cuuint64_t") public long pad(int index){
  return npad(address(),index);
}

/** 
 * Returns the value of the  {@code length} field. 
 */
@NativeType("size_t") public long length(){
  return nlength(address());
}

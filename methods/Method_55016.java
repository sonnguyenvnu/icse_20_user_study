/** 
 * Returns the value of the  {@code fnPtr} field. 
 */
@NativeType("void *") public long fnPtr(){
  return nfnPtr(address());
}

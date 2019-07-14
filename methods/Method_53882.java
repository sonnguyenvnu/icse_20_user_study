/** 
 * Returns the value of the  {@code user} field. 
 */
@NativeType("void *") public long user(){
  return nuser(address());
}

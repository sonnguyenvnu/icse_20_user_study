/** 
 * Returns the value of the  {@code sentinel} field. 
 */
@NativeType("char") public byte sentinel(){
  return nsentinel(address());
}

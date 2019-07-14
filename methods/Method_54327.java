/** 
 * Sets the specified value to the  {@code maxEncoders} field. 
 */
public BGFXInitLimits maxEncoders(@NativeType("uint16_t") short value){
  nmaxEncoders(address(),value);
  return this;
}

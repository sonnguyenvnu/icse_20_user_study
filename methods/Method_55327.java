/** 
 * Sets the specified value to the  {@code cBlueBits} field. 
 */
public PIXELFORMATDESCRIPTOR cBlueBits(@NativeType("BYTE") byte value){
  ncBlueBits(address(),value);
  return this;
}

/** 
 * Sets the specified value to the  {@code nVersion} field. 
 */
public PIXELFORMATDESCRIPTOR nVersion(@NativeType("WORD") short value){
  nnVersion(address(),value);
  return this;
}

/** 
 * Sets the specified value to the  {@code dwVisibleMask} field. 
 */
public PIXELFORMATDESCRIPTOR dwVisibleMask(@NativeType("DWORD") int value){
  ndwVisibleMask(address(),value);
  return this;
}

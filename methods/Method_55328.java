/** 
 * Sets the specified value to the  {@code dwLayerMask} field. 
 */
public PIXELFORMATDESCRIPTOR dwLayerMask(@NativeType("DWORD") int value){
  ndwLayerMask(address(),value);
  return this;
}

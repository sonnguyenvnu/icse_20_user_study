/** 
 * Unsafe version of  {@link #dwLayerMask}. 
 */
public static int ndwLayerMask(long struct){
  return UNSAFE.getInt(null,struct + PIXELFORMATDESCRIPTOR.DWLAYERMASK);
}

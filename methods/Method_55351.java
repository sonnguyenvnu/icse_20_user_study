/** 
 * Unsafe version of  {@link #iLayerType}. 
 */
public static byte niLayerType(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.ILAYERTYPE);
}

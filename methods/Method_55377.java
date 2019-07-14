/** 
 * Unsafe version of  {@link #iLayerType(byte) iLayerType}. 
 */
public static void niLayerType(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.ILAYERTYPE,value);
}

/** 
 * Unsafe version of  {@link #dwLayerMask(int) dwLayerMask}. 
 */
public static void ndwLayerMask(long struct,int value){
  UNSAFE.putInt(null,struct + PIXELFORMATDESCRIPTOR.DWLAYERMASK,value);
}

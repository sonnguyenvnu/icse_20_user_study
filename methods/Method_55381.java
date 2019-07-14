/** 
 * Unsafe version of  {@link #dwDamageMask(int) dwDamageMask}. 
 */
public static void ndwDamageMask(long struct,int value){
  UNSAFE.putInt(null,struct + PIXELFORMATDESCRIPTOR.DWDAMAGEMASK,value);
}

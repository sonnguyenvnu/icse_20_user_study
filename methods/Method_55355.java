/** 
 * Unsafe version of  {@link #dwDamageMask}. 
 */
public static int ndwDamageMask(long struct){
  return UNSAFE.getInt(null,struct + PIXELFORMATDESCRIPTOR.DWDAMAGEMASK);
}

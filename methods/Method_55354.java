/** 
 * Unsafe version of  {@link #dwVisibleMask}. 
 */
public static int ndwVisibleMask(long struct){
  return UNSAFE.getInt(null,struct + PIXELFORMATDESCRIPTOR.DWVISIBLEMASK);
}

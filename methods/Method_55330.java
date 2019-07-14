/** 
 * Unsafe version of  {@link #nSize}. 
 */
public static short nnSize(long struct){
  return UNSAFE.getShort(null,struct + PIXELFORMATDESCRIPTOR.NSIZE);
}

/** 
 * Unsafe version of  {@link #nVersion}. 
 */
public static short nnVersion(long struct){
  return UNSAFE.getShort(null,struct + PIXELFORMATDESCRIPTOR.NVERSION);
}

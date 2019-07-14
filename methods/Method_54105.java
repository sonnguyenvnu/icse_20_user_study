/** 
 * Unsafe version of  {@link #mHeight}. 
 */
public static int nmHeight(long struct){
  return UNSAFE.getInt(null,struct + AITexture.MHEIGHT);
}

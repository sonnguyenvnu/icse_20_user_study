/** 
 * Unsafe version of  {@link #mWidth}. 
 */
public static int nmWidth(long struct){
  return UNSAFE.getInt(null,struct + AITexture.MWIDTH);
}

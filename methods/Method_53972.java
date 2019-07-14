/** 
 * Unsafe version of  {@link #textures}. 
 */
public static int ntextures(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.TEXTURES);
}

/** 
 * Unsafe version of  {@link #achFormatHintString}. 
 */
public static String nachFormatHintString(long struct){
  return memASCII(struct + AITexture.ACHFORMATHINT);
}

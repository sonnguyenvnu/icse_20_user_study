/** 
 * Unsafe version of  {@link #achFormatHint}. 
 */
public static ByteBuffer nachFormatHint(long struct){
  return memByteBuffer(struct + AITexture.ACHFORMATHINT,9);
}

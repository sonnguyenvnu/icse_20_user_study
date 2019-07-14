/** 
 * Unsafe version of  {@link #formats}. 
 */
public static ShortBuffer nformats(long struct){
  return memShortBuffer(struct + BGFXCaps.FORMATS,BGFX_TEXTURE_FORMAT_COUNT);
}

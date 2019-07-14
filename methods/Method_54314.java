/** 
 * Unsafe version of  {@link #formats(int) formats}. 
 */
public static short nformats(long struct,int index){
  return UNSAFE.getShort(null,struct + BGFXCaps.FORMATS + check(index,BGFX_TEXTURE_FORMAT_COUNT) * 2);
}

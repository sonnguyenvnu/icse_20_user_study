/** 
 * Unsafe version of  {@link #mTextures(PointerBuffer) mTextures}. 
 */
public static void nmTextures(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIScene.MTEXTURES,memAddressSafe(value));
  nmNumTextures(struct,value == null ? 0 : value.remaining());
}

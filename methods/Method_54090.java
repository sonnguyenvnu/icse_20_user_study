/** 
 * Unsafe version of  {@link #mLights(PointerBuffer) mLights}. 
 */
public static void nmLights(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIScene.MLIGHTS,memAddressSafe(value));
  nmNumLights(struct,value == null ? 0 : value.remaining());
}

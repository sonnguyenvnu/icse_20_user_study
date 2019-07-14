/** 
 * Unsafe version of  {@link #mAnimations(PointerBuffer) mAnimations}. 
 */
public static void nmAnimations(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIScene.MANIMATIONS,memAddressSafe(value));
  nmNumAnimations(struct,value == null ? 0 : value.remaining());
}

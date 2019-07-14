/** 
 * Unsafe version of  {@link #mMaterials(PointerBuffer) mMaterials}. 
 */
public static void nmMaterials(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIScene.MMATERIALS,memAddressSafe(value));
  nmNumMaterials(struct,value == null ? 0 : value.remaining());
}

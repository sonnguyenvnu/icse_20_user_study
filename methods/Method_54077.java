/** 
 * Unsafe version of  {@link #mMaterials() mMaterials}. 
 */
@Nullable public static PointerBuffer nmMaterials(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AIScene.MMATERIALS),nmNumMaterials(struct));
}

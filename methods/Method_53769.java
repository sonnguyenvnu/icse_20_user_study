/** 
 * Unsafe version of  {@link #mMorphMeshChannels(PointerBuffer) mMorphMeshChannels}. 
 */
public static void nmMorphMeshChannels(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIAnimation.MMORPHMESHCHANNELS,memAddressSafe(value));
  nmNumMorphMeshChannels(struct,value == null ? 0 : value.remaining());
}

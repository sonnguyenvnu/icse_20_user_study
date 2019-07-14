/** 
 * Unsafe version of  {@link #mMeshChannels() mMeshChannels}. 
 */
@Nullable public static PointerBuffer nmMeshChannels(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AIAnimation.MMESHCHANNELS),nmNumMeshChannels(struct));
}

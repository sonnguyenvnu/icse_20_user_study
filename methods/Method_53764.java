/** 
 * Unsafe version of  {@link #mMorphMeshChannels() mMorphMeshChannels}. 
 */
@Nullable public static PointerBuffer nmMorphMeshChannels(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AIAnimation.MMORPHMESHCHANNELS),nmNumMorphMeshChannels(struct));
}

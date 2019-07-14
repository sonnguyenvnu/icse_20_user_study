/** 
 * Unsafe version of  {@link #mChannels() mChannels}. 
 */
@Nullable public static PointerBuffer nmChannels(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AIAnimation.MCHANNELS),nmNumChannels(struct));
}

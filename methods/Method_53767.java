/** 
 * Unsafe version of  {@link #mChannels(PointerBuffer) mChannels}. 
 */
public static void nmChannels(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIAnimation.MCHANNELS,memAddressSafe(value));
  nmNumChannels(struct,value == null ? 0 : value.remaining());
}

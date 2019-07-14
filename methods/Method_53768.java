/** 
 * Unsafe version of  {@link #mMeshChannels(PointerBuffer) mMeshChannels}. 
 */
public static void nmMeshChannels(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIAnimation.MMESHCHANNELS,memAddressSafe(value));
  nmNumMeshChannels(struct,value == null ? 0 : value.remaining());
}

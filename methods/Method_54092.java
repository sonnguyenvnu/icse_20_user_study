/** 
 * Unsafe version of  {@link #mCameras(PointerBuffer) mCameras}. 
 */
public static void nmCameras(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIScene.MCAMERAS,memAddressSafe(value));
  nmNumCameras(struct,value == null ? 0 : value.remaining());
}

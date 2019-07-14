/** 
 * Unsafe version of  {@link #mCameras() mCameras}. 
 */
@Nullable public static PointerBuffer nmCameras(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AIScene.MCAMERAS),nmNumCameras(struct));
}

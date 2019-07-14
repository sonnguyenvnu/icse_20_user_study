/** 
 * Unsafe version of  {@link #capture_begin}. 
 */
public static BGFXCaptureBeginCallback ncapture_begin(long struct){
  return BGFXCaptureBeginCallback.create(memGetAddress(struct + BGFXCallbackVtbl.CAPTURE_BEGIN));
}

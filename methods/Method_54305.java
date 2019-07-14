/** 
 * Unsafe version of  {@link #capture_end}. 
 */
public static BGFXCaptureEndCallback ncapture_end(long struct){
  return BGFXCaptureEndCallback.create(memGetAddress(struct + BGFXCallbackVtbl.CAPTURE_END));
}

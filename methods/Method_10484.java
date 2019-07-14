/** 
 * Locks the focus as the first step for a still image capture.
 */
private void lockFocus(){
  mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,CaptureRequest.CONTROL_AF_TRIGGER_START);
  try {
    mCaptureCallback.setState(PictureCaptureCallback.STATE_LOCKING);
    mCaptureSession.capture(mPreviewRequestBuilder.build(),mCaptureCallback,null);
  }
 catch (  CameraAccessException e) {
    Log.e(TAG,"Failed to lock focus.",e);
  }
}

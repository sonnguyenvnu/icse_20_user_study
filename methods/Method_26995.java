/** 
 * Unlocks the auto-focus and restart camera preview. This is supposed to be called after capturing a still picture.
 */
void unlockFocus(){
  mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
  try {
    mCaptureSession.capture(mPreviewRequestBuilder.build(),mCaptureCallback,null);
    updateAutoFocus();
    updateFlash();
    mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,CaptureRequest.CONTROL_AF_TRIGGER_IDLE);
    mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(),mCaptureCallback,null);
    mCaptureCallback.setState(PictureCaptureCallback.STATE_PREVIEW);
  }
 catch (  CameraAccessException e) {
    Log.e(TAG,"Failed to restart camera preview.",e);
  }
}

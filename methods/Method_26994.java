/** 
 * Captures a still picture.
 */
void captureStillPicture(){
  try {
    CaptureRequest.Builder captureRequestBuilder=mCamera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
    captureRequestBuilder.addTarget(mImageReader.getSurface());
    captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_MODE));
switch (mFlash) {
case Constants.FLASH_OFF:
      captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
    captureRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
  break;
case Constants.FLASH_ON:
captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
break;
case Constants.FLASH_TORCH:
captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
captureRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_TORCH);
break;
case Constants.FLASH_AUTO:
captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
break;
case Constants.FLASH_RED_EYE:
captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
break;
}
@SuppressWarnings("ConstantConditions") int sensorOrientation=mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,(sensorOrientation + mDisplayOrientation * (mFacing == Constants.FACING_FRONT ? 1 : -1) + 360) % 360);
mCaptureSession.stopRepeating();
mCaptureSession.capture(captureRequestBuilder.build(),new CameraCaptureSession.CaptureCallback(){
@Override public void onCaptureCompleted(@NonNull CameraCaptureSession session,@NonNull CaptureRequest request,@NonNull TotalCaptureResult result){
unlockFocus();
}
}
,null);
}
 catch (CameraAccessException e) {
Log.e(TAG,"Cannot capture a still picture.",e);
}
}

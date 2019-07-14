/** 
 * Updates the internal state of flash to  {@link #mFlash}.
 */
void updateFlash(){
switch (mFlash) {
case Constants.FLASH_OFF:
    mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
  mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
break;
case Constants.FLASH_ON:
mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
break;
case Constants.FLASH_TORCH:
mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_TORCH);
break;
case Constants.FLASH_AUTO:
mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
break;
case Constants.FLASH_RED_EYE:
mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE);
mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
break;
}
}

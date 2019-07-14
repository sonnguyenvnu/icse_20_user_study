/** 
 * @return {@code true} if {@link #mCameraParameters} was modified.
 */
private boolean setFlashInternal(int flash){
  if (isCameraOpened()) {
    List<String> modes=mCameraParameters.getSupportedFlashModes();
    String mode=FLASH_MODES.get(flash);
    if (modes != null && modes.contains(mode)) {
      mCameraParameters.setFlashMode(mode);
      mFlash=flash;
      return true;
    }
    String currentMode=FLASH_MODES.get(mFlash);
    if (modes == null || !modes.contains(currentMode)) {
      mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      mFlash=Constants.FLASH_OFF;
      return true;
    }
    return false;
  }
 else {
    mFlash=flash;
    return false;
  }
}

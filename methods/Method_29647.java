@Override void setDisplayAndDeviceOrientation(int displayOrientation,int deviceOrientation){
  this.mDisplayOrientation=displayOrientation;
  this.mDeviceOrientation=deviceOrientation;
synchronized (mCameraLock) {
    if (isCameraOpened()) {
      try {
        mCamera.setDisplayOrientation(calculatePreviewRotation());
      }
 catch (      RuntimeException e) {
      }
    }
  }
}

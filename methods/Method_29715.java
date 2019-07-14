void dispatchOnDisplayOrDeviceOrientationChanged(int displayOrientation){
  mLastKnownDisplayOrientation=displayOrientation;
  if (mOrientationEventListener.canDetectOrientation()) {
    onDisplayOrDeviceOrientationChanged(displayOrientation,mLastKnownDeviceOrientation);
  }
 else {
    onDisplayOrDeviceOrientationChanged(displayOrientation,displayOrientation);
  }
}

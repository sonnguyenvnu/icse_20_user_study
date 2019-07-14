void dispatchOnDisplayOrientationChanged(int displayOrientation){
  mLastKnownDisplayOrientation=displayOrientation;
  onDisplayOrientationChanged(displayOrientation);
}

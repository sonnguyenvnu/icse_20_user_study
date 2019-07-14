@Override boolean getAutoFocus(){
  if (!isCameraOpened()) {
    return mAutoFocus;
  }
  String focusMode=mCameraParameters.getFocusMode();
  return focusMode != null && focusMode.contains("continuous");
}

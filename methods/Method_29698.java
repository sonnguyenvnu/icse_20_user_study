@Override protected void onTapToFocus(float x,float y){
  if (mFocus == CameraKit.Constants.FOCUS_TAP || mFocus == CameraKit.Constants.FOCUS_TAP_WITH_MARKER) {
    focusMarkerLayout.focus(x,y);
    float px=x - getPreviewImpl().getX();
    float py=y - getPreviewImpl().getY();
    mCameraImpl.setFocusArea(px / (float)getPreviewImpl().getWidth(),py / (float)getPreviewImpl().getHeight());
  }
}

@Override protected void onZoom(float modifier,boolean start){
  if (mPinchToZoom) {
    mCameraImpl.modifyZoom((modifier - 1) * 0.8f + 1);
  }
}

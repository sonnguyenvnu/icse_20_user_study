public void onStop(){
  if (isInEditMode()) {
    return;
  }
  mCameraPreview.stop();
}

public void setDisplayOrientation(int displayOrientation){
  if (mPreviewSize != null) {
    setPreviewSize(mPreviewSize,displayOrientation);
  }
 else {
    this.mDisplayOrientation=displayOrientation;
  }
}

public void initialize(boolean mirror,int displayOrientation){
  mPreviewWidth=ApplicationScreen.getPreviewSurfaceLayoutWidth();
  mPreviewHeight=ApplicationScreen.getPreviewSurfaceLayoutHeight();
  Matrix matrix=new Matrix();
  Util.prepareMatrix(matrix,mirror,CameraController.getSensorOrientation(mirror),mPreviewWidth,mPreviewHeight);
  matrix.invert(mMatrix);
  mInitialized=true;
}

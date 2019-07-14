private void adjustAspectRatio(int previewWidth,int previewHeight,int rotation){
  txform.reset();
  int viewWidth=getWidth();
  int viewHeight=getHeight();
  float viewCenterX=viewWidth / 2;
  float viewCenterY=viewHeight / 2;
  float scale;
  if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
    scale=Math.max((float)(viewHeight + clipTop) / previewWidth,(float)(viewWidth + clipLeft) / previewHeight);
  }
 else {
    scale=Math.max((float)(viewHeight + clipTop) / previewHeight,(float)(viewWidth + clipLeft) / previewWidth);
  }
  float previewWidthScaled=previewWidth * scale;
  float previewHeightScaled=previewHeight * scale;
  float scaleX=previewHeightScaled / (viewWidth);
  float scaleY=previewWidthScaled / (viewHeight);
  txform.postScale(scaleX,scaleY,viewCenterX,viewCenterY);
  if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
    txform.postRotate(90 * (rotation - 2),viewCenterX,viewCenterY);
  }
 else {
    if (Surface.ROTATION_180 == rotation) {
      txform.postRotate(180,viewCenterX,viewCenterY);
    }
  }
  if (mirror) {
    txform.postScale(-1,1,viewCenterX,viewCenterY);
  }
  if (clipTop != 0 || clipLeft != 0) {
    txform.postTranslate(-clipLeft / 2,-clipTop / 2);
  }
  textureView.setTransform(txform);
  Matrix matrix=new Matrix();
  matrix.postRotate(cameraSession.getDisplayOrientation());
  matrix.postScale(viewWidth / 2000f,viewHeight / 2000f);
  matrix.postTranslate(viewWidth / 2f,viewHeight / 2f);
  matrix.invert(this.matrix);
}

private void handleRotate(double rotate){
  rotate=RxRotateTool.getNormalizedAngle(rotate);
  mRotateAngle=rotate;
  invalidate();
}

@Keep public void setCameraOpenProgress(float value){
  if (cameraView == null) {
    return;
  }
  cameraOpenProgress=value;
  float startWidth=animateCameraValues[1];
  float startHeight=animateCameraValues[2];
  boolean isPortrait=AndroidUtilities.displaySize.x < AndroidUtilities.displaySize.y;
  float endWidth;
  float endHeight;
  if (isPortrait) {
    endWidth=container.getWidth() - getLeftInset() - getRightInset();
    endHeight=container.getHeight();
  }
 else {
    endWidth=container.getWidth() - getLeftInset() - getRightInset();
    endHeight=container.getHeight();
  }
  if (value == 0) {
    cameraView.setClipLeft(cameraViewOffsetX);
    cameraView.setClipTop(cameraViewOffsetY);
    cameraView.setTranslationX(cameraViewLocation[0]);
    cameraView.setTranslationY(cameraViewLocation[1]);
    cameraIcon.setTranslationX(cameraViewLocation[0]);
    cameraIcon.setTranslationY(cameraViewLocation[1]);
  }
 else   if (cameraView.getTranslationX() != 0 || cameraView.getTranslationY() != 0) {
    cameraView.setTranslationX(0);
    cameraView.setTranslationY(0);
  }
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)cameraView.getLayoutParams();
  layoutParams.width=(int)(startWidth + (endWidth - startWidth) * value);
  layoutParams.height=(int)(startHeight + (endHeight - startHeight) * value);
  if (value != 0) {
    cameraView.setClipLeft((int)(cameraViewOffsetX * (1.0f - value)));
    cameraView.setClipTop((int)(cameraViewOffsetY * (1.0f - value)));
    layoutParams.leftMargin=(int)(cameraViewLocation[0] * (1.0f - value));
    layoutParams.topMargin=(int)(animateCameraValues[0] + (cameraViewLocation[1] - animateCameraValues[0]) * (1.0f - value));
  }
 else {
    layoutParams.leftMargin=0;
    layoutParams.topMargin=0;
  }
  cameraView.setLayoutParams(layoutParams);
  if (value <= 0.5f) {
    cameraIcon.setAlpha(1.0f - value / 0.5f);
  }
 else {
    cameraIcon.setAlpha(0.0f);
  }
}

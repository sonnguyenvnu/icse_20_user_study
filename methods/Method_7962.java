private void updateRadialProgressBackground(){
  if (drawRadialCheckBackground) {
    return;
  }
  boolean forcePressed=(isHighlighted || isPressed || isPressed()) && (!drawPhotoImage || !photoImage.hasBitmapImage());
  radialProgress.setPressed(forcePressed || buttonPressed != 0,false);
  if (hasMiniProgress != 0) {
    radialProgress.setPressed(forcePressed || miniButtonPressed != 0,true);
  }
  videoRadialProgress.setPressed(forcePressed || videoButtonPressed != 0,false);
}

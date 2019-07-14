private void drawTextProgressPosition(){
  clearTextProgressAlign();
  int textProgressWidth=tvProgress.getMeasuredWidth() + (getTextProgressMargin() * 2);
  float ratio=getMax() / getProgress();
  int progressWidth=(int)((getLayoutWidth() - (getPadding() * 2)) / ratio);
  if (textProgressWidth + textProgressMargin < progressWidth) {
    alignTextProgressInsideProgress();
  }
 else {
    alignTextProgressOutsideProgress();
  }
}

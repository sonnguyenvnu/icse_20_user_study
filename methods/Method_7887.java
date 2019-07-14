private void goToPrev(){
  float extra=0;
  if (scale != 1) {
    extra=(getContainerViewWidth() - centerImage.getImageWidth()) / 2 * scale;
  }
  switchImageAfterAnimation=2;
  animateTo(scale,maxX + getContainerViewWidth() + extra + AndroidUtilities.dp(30) / 2,translationY,false);
}

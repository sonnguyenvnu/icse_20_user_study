private void goToNext(){
  float extra=0;
  if (scale != 1) {
    extra=(getContainerViewWidth() - centerImage.getImageWidth()) / 2 * scale;
  }
  switchImageAfterAnimation=1;
  animateTo(scale,minX - getContainerViewWidth() - extra - AndroidUtilities.dp(30) / 2,translationY,false);
}

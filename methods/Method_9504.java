private void setCropTranslations(boolean animated){
  if (sendPhotoType != SELECT_TYPE_AVATAR) {
    return;
  }
  int bitmapWidth=centerImage.getBitmapWidth();
  int bitmapHeight=centerImage.getBitmapHeight();
  if (bitmapWidth == 0 || bitmapHeight == 0) {
    return;
  }
  float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
  float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
  float scaleFinal=scaleX > scaleY ? scaleY : scaleX;
  float minSide=Math.min(getContainerViewWidth(1),getContainerViewHeight(1));
  float newScaleX=minSide / (float)bitmapWidth;
  float newScaleY=minSide / (float)bitmapHeight;
  float newScale=newScaleX > newScaleY ? newScaleX : newScaleY;
  if (animated) {
    animationStartTime=System.currentTimeMillis();
    animateToX=getLeftInset() / 2 - getRightInset() / 2;
    if (currentEditMode == 2) {
      animateToY=AndroidUtilities.dp(92) - AndroidUtilities.dp(24 + 32);
    }
 else     if (currentEditMode == 3) {
      animateToY=AndroidUtilities.dp(44) - AndroidUtilities.dp(24 + 32);
    }
    animateToScale=newScale / scaleFinal;
    zoomAnimation=true;
  }
 else {
    animationStartTime=0;
    translationX=getLeftInset() / 2 - getRightInset() / 2;
    translationY=-AndroidUtilities.dp(24 + 32) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight / 2 : 0);
    scale=newScale / scaleFinal;
    updateMinMax(scale);
  }
}

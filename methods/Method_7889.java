private void drawContent(Canvas canvas){
  if (photoAnimationInProgress == 1 || !isPhotoVisible && photoAnimationInProgress != 2) {
    return;
  }
  float currentTranslationY;
  float currentTranslationX;
  float currentScale;
  float aty=-1;
  if (imageMoveAnimation != null) {
    if (!scroller.isFinished()) {
      scroller.abortAnimation();
    }
    float ts=scale + (animateToScale - scale) * animationValue;
    float tx=translationX + (animateToX - translationX) * animationValue;
    float ty=translationY + (animateToY - translationY) * animationValue;
    if (animateToScale == 1 && scale == 1 && translationX == 0) {
      aty=ty;
    }
    currentScale=ts;
    currentTranslationY=ty;
    currentTranslationX=tx;
    photoContainerView.invalidate();
  }
 else {
    if (animationStartTime != 0) {
      translationX=animateToX;
      translationY=animateToY;
      scale=animateToScale;
      animationStartTime=0;
      updateMinMax(scale);
      zoomAnimation=false;
    }
    if (!scroller.isFinished()) {
      if (scroller.computeScrollOffset()) {
        if (scroller.getStartX() < maxX && scroller.getStartX() > minX) {
          translationX=scroller.getCurrX();
        }
        if (scroller.getStartY() < maxY && scroller.getStartY() > minY) {
          translationY=scroller.getCurrY();
        }
        photoContainerView.invalidate();
      }
    }
    if (switchImageAfterAnimation != 0) {
      if (switchImageAfterAnimation == 1) {
        AndroidUtilities.runOnUIThread(() -> setImageIndex(currentIndex + 1,false));
      }
 else       if (switchImageAfterAnimation == 2) {
        AndroidUtilities.runOnUIThread(() -> setImageIndex(currentIndex - 1,false));
      }
      switchImageAfterAnimation=0;
    }
    currentScale=scale;
    currentTranslationY=translationY;
    currentTranslationX=translationX;
    if (!moving) {
      aty=translationY;
    }
  }
  if (photoAnimationInProgress != 2) {
    if (scale == 1 && aty != -1 && !zoomAnimation) {
      float maxValue=getContainerViewHeight() / 4.0f;
      photoBackgroundDrawable.setAlpha((int)Math.max(127,255 * (1.0f - (Math.min(Math.abs(aty),maxValue) / maxValue))));
    }
 else {
      photoBackgroundDrawable.setAlpha(255);
    }
  }
  ImageReceiver sideImage=null;
  if (scale >= 1.0f && !zoomAnimation && !zooming) {
    if (currentTranslationX > maxX + AndroidUtilities.dp(5)) {
      sideImage=leftImage;
    }
 else     if (currentTranslationX < minX - AndroidUtilities.dp(5)) {
      sideImage=rightImage;
    }
 else {
      groupedPhotosListView.setMoveProgress(0.0f);
    }
  }
  changingPage=sideImage != null;
  if (sideImage == rightImage) {
    float tranlateX=currentTranslationX;
    float scaleDiff=0;
    float alpha=1;
    if (!zoomAnimation && tranlateX < minX) {
      alpha=Math.min(1.0f,(minX - tranlateX) / canvas.getWidth());
      scaleDiff=(1.0f - alpha) * 0.3f;
      tranlateX=-canvas.getWidth() - AndroidUtilities.dp(30) / 2;
    }
    if (sideImage.hasBitmapImage()) {
      canvas.save();
      canvas.translate(getContainerViewWidth() / 2,getContainerViewHeight() / 2);
      canvas.translate(canvas.getWidth() + AndroidUtilities.dp(30) / 2 + tranlateX,0);
      canvas.scale(1.0f - scaleDiff,1.0f - scaleDiff);
      int bitmapWidth=sideImage.getBitmapWidth();
      int bitmapHeight=sideImage.getBitmapHeight();
      float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
      float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
      float scale=scaleX > scaleY ? scaleY : scaleX;
      int width=(int)(bitmapWidth * scale);
      int height=(int)(bitmapHeight * scale);
      sideImage.setAlpha(alpha);
      sideImage.setImageCoords(-width / 2,-height / 2,width,height);
      sideImage.draw(canvas);
      canvas.restore();
    }
    groupedPhotosListView.setMoveProgress(-alpha);
    canvas.save();
    canvas.translate(tranlateX,currentTranslationY / currentScale);
    canvas.translate((canvas.getWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2,-currentTranslationY / currentScale);
    radialProgressViews[1].setScale(1.0f - scaleDiff);
    radialProgressViews[1].setAlpha(alpha);
    radialProgressViews[1].onDraw(canvas);
    canvas.restore();
  }
  float translateX=currentTranslationX;
  float scaleDiff=0;
  float alpha=1;
  if (!zoomAnimation && translateX > maxX) {
    alpha=Math.min(1.0f,(translateX - maxX) / canvas.getWidth());
    scaleDiff=alpha * 0.3f;
    alpha=1.0f - alpha;
    translateX=maxX;
  }
  boolean drawTextureView=aspectRatioFrameLayout != null && aspectRatioFrameLayout.getVisibility() == View.VISIBLE;
  if (centerImage.hasBitmapImage()) {
    canvas.save();
    canvas.translate(getContainerViewWidth() / 2,getContainerViewHeight() / 2);
    canvas.translate(translateX,currentTranslationY);
    canvas.scale(currentScale - scaleDiff,currentScale - scaleDiff);
    int bitmapWidth=centerImage.getBitmapWidth();
    int bitmapHeight=centerImage.getBitmapHeight();
    if (drawTextureView && textureUploaded) {
      float scale1=bitmapWidth / (float)bitmapHeight;
      float scale2=videoTextureView.getMeasuredWidth() / (float)videoTextureView.getMeasuredHeight();
      if (Math.abs(scale1 - scale2) > 0.01f) {
        bitmapWidth=videoTextureView.getMeasuredWidth();
        bitmapHeight=videoTextureView.getMeasuredHeight();
      }
    }
    float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
    float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
    float scale=scaleX > scaleY ? scaleY : scaleX;
    int width=(int)(bitmapWidth * scale);
    int height=(int)(bitmapHeight * scale);
    if (!drawTextureView || !textureUploaded || !videoCrossfadeStarted || videoCrossfadeAlpha != 1.0f) {
      centerImage.setAlpha(alpha);
      centerImage.setImageCoords(-width / 2,-height / 2,width,height);
      centerImage.draw(canvas);
    }
    if (drawTextureView) {
      if (!videoCrossfadeStarted && textureUploaded) {
        videoCrossfadeStarted=true;
        videoCrossfadeAlpha=0.0f;
        videoCrossfadeAlphaLastTime=System.currentTimeMillis();
      }
      canvas.translate(-width / 2,-height / 2);
      videoTextureView.setAlpha(alpha * videoCrossfadeAlpha);
      aspectRatioFrameLayout.draw(canvas);
      if (videoCrossfadeStarted && videoCrossfadeAlpha < 1.0f) {
        long newUpdateTime=System.currentTimeMillis();
        long dt=newUpdateTime - videoCrossfadeAlphaLastTime;
        videoCrossfadeAlphaLastTime=newUpdateTime;
        videoCrossfadeAlpha+=dt / 300.0f;
        photoContainerView.invalidate();
        if (videoCrossfadeAlpha > 1.0f) {
          videoCrossfadeAlpha=1.0f;
        }
      }
    }
    canvas.restore();
  }
  if (!drawTextureView && bottomLayout.getVisibility() != View.VISIBLE) {
    canvas.save();
    canvas.translate(translateX,currentTranslationY / currentScale);
    radialProgressViews[0].setScale(1.0f - scaleDiff);
    radialProgressViews[0].setAlpha(alpha);
    radialProgressViews[0].onDraw(canvas);
    canvas.restore();
  }
  if (sideImage == leftImage) {
    if (sideImage.hasBitmapImage()) {
      canvas.save();
      canvas.translate(getContainerViewWidth() / 2,getContainerViewHeight() / 2);
      canvas.translate(-(canvas.getWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2 + currentTranslationX,0);
      int bitmapWidth=sideImage.getBitmapWidth();
      int bitmapHeight=sideImage.getBitmapHeight();
      float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
      float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
      float scale=scaleX > scaleY ? scaleY : scaleX;
      int width=(int)(bitmapWidth * scale);
      int height=(int)(bitmapHeight * scale);
      sideImage.setAlpha(1.0f);
      sideImage.setImageCoords(-width / 2,-height / 2,width,height);
      sideImage.draw(canvas);
      canvas.restore();
    }
    groupedPhotosListView.setMoveProgress(1.0f - alpha);
    canvas.save();
    canvas.translate(currentTranslationX,currentTranslationY / currentScale);
    canvas.translate(-(canvas.getWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2,-currentTranslationY / currentScale);
    radialProgressViews[2].setScale(1.0f);
    radialProgressViews[2].setAlpha(1.0f);
    radialProgressViews[2].onDraw(canvas);
    canvas.restore();
  }
}

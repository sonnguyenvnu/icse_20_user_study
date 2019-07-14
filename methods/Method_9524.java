@SuppressLint({"NewApi","DrawAllocation"}) private void onDraw(Canvas canvas){
  if (animationInProgress == 1 || !isVisible && animationInProgress != 2 && !pipAnimationInProgress) {
    return;
  }
  if (padImageForHorizontalInsets) {
    canvas.save();
    canvas.translate(getLeftInset() / 2 - getRightInset() / 2,0);
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
    containerView.invalidate();
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
        containerView.invalidate();
      }
    }
    if (switchImageAfterAnimation != 0) {
      openedFullScreenVideo=false;
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
  if (animationInProgress != 2 && !pipAnimationInProgress && !isInline) {
    if (currentEditMode == 0 && sendPhotoType != SELECT_TYPE_AVATAR && scale == 1 && aty != -1 && !zoomAnimation) {
      float maxValue=getContainerViewHeight() / 4.0f;
      backgroundDrawable.setAlpha((int)Math.max(127,255 * (1.0f - (Math.min(Math.abs(aty),maxValue) / maxValue))));
    }
 else {
      backgroundDrawable.setAlpha(255);
    }
  }
  ImageReceiver sideImage=null;
  if (currentEditMode == 0 && sendPhotoType != SELECT_TYPE_AVATAR) {
    if (scale >= 1.0f && !zoomAnimation && !zooming) {
      if (currentTranslationX > maxX + AndroidUtilities.dp(5)) {
        sideImage=leftImage;
      }
 else       if (currentTranslationX < minX - AndroidUtilities.dp(5)) {
        sideImage=rightImage;
      }
 else {
        groupedPhotosListView.setMoveProgress(0.0f);
      }
    }
    changingPage=sideImage != null;
  }
  if (sideImage == rightImage) {
    float translateX=currentTranslationX;
    float scaleDiff=0;
    float alpha=1;
    if (!zoomAnimation && translateX < minX) {
      alpha=Math.min(1.0f,(minX - translateX) / getContainerViewWidth());
      scaleDiff=(1.0f - alpha) * 0.3f;
      translateX=-getContainerViewWidth() - AndroidUtilities.dp(30) / 2;
    }
    if (sideImage.hasBitmapImage()) {
      canvas.save();
      canvas.translate(getContainerViewWidth() / 2,getContainerViewHeight() / 2);
      canvas.translate(getContainerViewWidth() + AndroidUtilities.dp(30) / 2 + translateX,0);
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
    canvas.translate(translateX,currentTranslationY / currentScale);
    canvas.translate((getContainerViewWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2,-currentTranslationY / currentScale);
    photoProgressViews[1].setScale(1.0f - scaleDiff);
    photoProgressViews[1].setAlpha(alpha);
    photoProgressViews[1].onDraw(canvas);
    canvas.restore();
  }
  float translateX=currentTranslationX;
  float scaleDiff=0;
  float alpha=1;
  if (!zoomAnimation && translateX > maxX && currentEditMode == 0 && sendPhotoType != SELECT_TYPE_AVATAR) {
    alpha=Math.min(1.0f,(translateX - maxX) / getContainerViewWidth());
    scaleDiff=alpha * 0.3f;
    alpha=1.0f - alpha;
    translateX=maxX;
  }
  boolean drawTextureView=aspectRatioFrameLayout != null && aspectRatioFrameLayout.getVisibility() == View.VISIBLE;
  if (centerImage.hasBitmapImage() || drawTextureView && textureUploaded) {
    canvas.save();
    canvas.translate(getContainerViewWidth() / 2 + getAdditionX(),getContainerViewHeight() / 2 + getAdditionY());
    canvas.translate(translateX,currentTranslationY);
    canvas.scale(currentScale - scaleDiff,currentScale - scaleDiff);
    int bitmapWidth;
    int bitmapHeight;
    if (drawTextureView && textureUploaded) {
      bitmapWidth=videoTextureView.getMeasuredWidth();
      bitmapHeight=videoTextureView.getMeasuredHeight();
    }
 else {
      bitmapWidth=centerImage.getBitmapWidth();
      bitmapHeight=centerImage.getBitmapHeight();
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
      scaleX=(float)canvas.getWidth() / (float)bitmapWidth;
      scaleY=(float)canvas.getHeight() / (float)bitmapHeight;
      scale=scaleX > scaleY ? scaleY : scaleX;
      height=(int)(bitmapHeight * scale);
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
        videoCrossfadeAlpha+=dt / (playerInjected ? 100.0f : 200.0f);
        containerView.invalidate();
        if (videoCrossfadeAlpha > 1.0f) {
          videoCrossfadeAlpha=1.0f;
        }
      }
    }
    canvas.restore();
  }
  boolean drawProgress;
  if (isCurrentVideo) {
    drawProgress=progressView.getVisibility() != View.VISIBLE && (videoPlayer == null || !videoPlayer.isPlaying());
  }
 else {
    drawProgress=!drawTextureView && videoPlayerControlFrameLayout.getVisibility() != View.VISIBLE;
    if (drawProgress && currentAnimation != null && !currentAnimation.isLoadingStream()) {
      drawProgress=false;
    }
  }
  if (drawProgress) {
    canvas.save();
    canvas.translate(translateX,currentTranslationY / currentScale);
    photoProgressViews[0].setScale(1.0f - scaleDiff);
    photoProgressViews[0].setAlpha(alpha);
    photoProgressViews[0].onDraw(canvas);
    canvas.restore();
  }
  if (!pipAnimationInProgress && (miniProgressView.getVisibility() == View.VISIBLE || miniProgressAnimator != null)) {
    canvas.save();
    canvas.translate(miniProgressView.getLeft() + translateX,miniProgressView.getTop() + currentTranslationY / currentScale);
    miniProgressView.draw(canvas);
    canvas.restore();
  }
  if (sideImage == leftImage) {
    if (sideImage.hasBitmapImage()) {
      canvas.save();
      canvas.translate(getContainerViewWidth() / 2,getContainerViewHeight() / 2);
      canvas.translate(-(getContainerViewWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2 + currentTranslationX,0);
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
    canvas.translate(-(getContainerViewWidth() * (scale + 1) + AndroidUtilities.dp(30)) / 2,-currentTranslationY / currentScale);
    photoProgressViews[2].setScale(1.0f);
    photoProgressViews[2].setAlpha(1.0f);
    photoProgressViews[2].onDraw(canvas);
    canvas.restore();
  }
  if (waitingForDraw != 0) {
    waitingForDraw--;
    if (waitingForDraw == 0) {
      if (textureImageView != null) {
        try {
          currentBitmap=Bitmaps.createBitmap(videoTextureView.getWidth(),videoTextureView.getHeight(),Bitmap.Config.ARGB_8888);
          changedTextureView.getBitmap(currentBitmap);
        }
 catch (        Throwable e) {
          if (currentBitmap != null) {
            currentBitmap.recycle();
            currentBitmap=null;
          }
          FileLog.e(e);
        }
        if (currentBitmap != null) {
          textureImageView.setVisibility(View.VISIBLE);
          textureImageView.setImageBitmap(currentBitmap);
        }
 else {
          textureImageView.setImageDrawable(null);
        }
      }
      pipVideoView.close();
      pipVideoView=null;
    }
 else {
      containerView.invalidate();
    }
  }
  if (padImageForHorizontalInsets) {
    canvas.restore();
  }
  if (aspectRatioFrameLayout != null && videoForwardDrawable.isAnimating()) {
    int h=(int)(aspectRatioFrameLayout.getMeasuredHeight() * (scale - 1.0f)) / 2;
    videoForwardDrawable.setBounds(aspectRatioFrameLayout.getLeft(),aspectRatioFrameLayout.getTop() - h + (int)(currentTranslationY / currentScale),aspectRatioFrameLayout.getRight(),aspectRatioFrameLayout.getBottom() + h + (int)(currentTranslationY / currentScale));
    videoForwardDrawable.draw(canvas);
  }
}

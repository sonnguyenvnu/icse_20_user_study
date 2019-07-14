public void closePhoto(boolean animated,boolean fromEditMode){
  if (!fromEditMode && currentEditMode != 0) {
    if (currentEditMode == 3 && photoPaintView != null) {
      photoPaintView.maybeShowDismissalAlert(this,parentActivity,() -> switchToEditMode(0));
      return;
    }
    switchToEditMode(0);
    return;
  }
  if (qualityChooseView != null && qualityChooseView.getTag() != null) {
    qualityPicker.cancelButton.callOnClick();
    return;
  }
  openedFullScreenVideo=false;
  try {
    if (visibleDialog != null) {
      visibleDialog.dismiss();
      visibleDialog=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (Build.VERSION.SDK_INT >= 21 && actionBar != null) {
    int flagsToClear=containerView.getSystemUiVisibility() & (View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    if (flagsToClear != 0) {
      containerView.setSystemUiVisibility(containerView.getSystemUiVisibility() & ~flagsToClear);
    }
  }
  if (currentEditMode != 0) {
    if (currentEditMode == 2) {
      photoFilterView.shutdown();
      containerView.removeView(photoFilterView);
      photoFilterView=null;
    }
 else     if (currentEditMode == 1) {
      editorDoneLayout.setVisibility(View.GONE);
      photoCropView.setVisibility(View.GONE);
    }
 else     if (currentEditMode == 3) {
      photoPaintView.shutdown();
      containerView.removeView(photoPaintView);
      photoPaintView=null;
    }
    currentEditMode=0;
  }
 else   if (sendPhotoType == SELECT_TYPE_AVATAR) {
    photoCropView.setVisibility(View.GONE);
  }
  if (parentActivity == null || !isInline && !isVisible || checkAnimation() || placeProvider == null) {
    return;
  }
  if (captionEditText.hideActionMode() && !fromEditMode) {
    return;
  }
  final PlaceProviderObject object=placeProvider.getPlaceForPhoto(currentMessageObject,getFileLocation(currentFileLocation),currentIndex,true);
  if (videoPlayer != null && object != null) {
    AnimatedFileDrawable animation=object.imageReceiver.getAnimation();
    if (animation != null) {
      if (textureUploaded) {
        Bitmap bitmap=animation.getAnimatedBitmap();
        if (bitmap != null) {
          try {
            Bitmap src=videoTextureView.getBitmap(bitmap.getWidth(),bitmap.getHeight());
            Canvas canvas=new Canvas(bitmap);
            canvas.drawBitmap(src,0,0,null);
            src.recycle();
          }
 catch (          Throwable e) {
            FileLog.e(e);
          }
        }
      }
      animation.seekTo(videoPlayer.getCurrentPosition(),!FileLoader.getInstance(currentMessageObject.currentAccount).isLoadingVideo(currentMessageObject.getDocument(),true));
      object.imageReceiver.setAllowStartAnimation(true);
      object.imageReceiver.startAnimation();
    }
  }
  releasePlayer(true);
  captionEditText.onDestroy();
  parentChatActivity=null;
  removeObservers();
  isActionBarVisible=false;
  if (velocityTracker != null) {
    velocityTracker.recycle();
    velocityTracker=null;
  }
  if (isInline) {
    isInline=false;
    animationInProgress=0;
    onPhotoClosed(object);
    containerView.setScaleX(1.0f);
    containerView.setScaleY(1.0f);
  }
 else {
    if (animated) {
      animationInProgress=1;
      animatingImageView.setVisibility(View.VISIBLE);
      containerView.invalidate();
      AnimatorSet animatorSet=new AnimatorSet();
      final ViewGroup.LayoutParams layoutParams=animatingImageView.getLayoutParams();
      RectF drawRegion=null;
      if (object != null) {
        animatingImageView.setNeedRadius(object.radius != 0);
        drawRegion=object.imageReceiver.getDrawRegion();
        layoutParams.width=(int)drawRegion.width();
        layoutParams.height=(int)drawRegion.height();
        int orientation=object.imageReceiver.getOrientation();
        int animatedOrientation=object.imageReceiver.getAnimatedOrientation();
        if (animatedOrientation != 0) {
          orientation=animatedOrientation;
        }
        animatingImageView.setOrientation(orientation);
        animatingImageView.setImageBitmap(object.thumb);
      }
 else {
        animatingImageView.setNeedRadius(false);
        layoutParams.width=centerImage.getImageWidth();
        layoutParams.height=centerImage.getImageHeight();
        animatingImageView.setOrientation(centerImage.getOrientation());
        animatingImageView.setImageBitmap(centerImage.getBitmapSafe());
      }
      animatingImageView.setLayoutParams(layoutParams);
      float scaleX=(float)windowView.getMeasuredWidth() / layoutParams.width;
      float scaleY=(float)(AndroidUtilities.displaySize.y + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0)) / layoutParams.height;
      float scale2=scaleX > scaleY ? scaleY : scaleX;
      float width=layoutParams.width * scale * scale2;
      float height=layoutParams.height * scale * scale2;
      float xPos=(windowView.getMeasuredWidth() - width) / 2.0f;
      float yPos=((AndroidUtilities.displaySize.y + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0)) - height) / 2.0f;
      animatingImageView.setTranslationX(xPos + translationX);
      animatingImageView.setTranslationY(yPos + translationY);
      animatingImageView.setScaleX(scale * scale2);
      animatingImageView.setScaleY(scale * scale2);
      if (object != null) {
        object.imageReceiver.setVisible(false,true);
        int clipHorizontal=(int)Math.abs(drawRegion.left - object.imageReceiver.getImageX());
        int clipVertical=(int)Math.abs(drawRegion.top - object.imageReceiver.getImageY());
        int[] coords2=new int[2];
        object.parentView.getLocationInWindow(coords2);
        int clipTop=(int)(coords2[1] - (Build.VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight) - (object.viewY + drawRegion.top) + object.clipTopAddition);
        if (clipTop < 0) {
          clipTop=0;
        }
        int clipBottom=(int)((object.viewY + drawRegion.top + (drawRegion.bottom - drawRegion.top)) - (coords2[1] + object.parentView.getHeight() - (Build.VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight)) + object.clipBottomAddition);
        if (clipBottom < 0) {
          clipBottom=0;
        }
        clipTop=Math.max(clipTop,clipVertical);
        clipBottom=Math.max(clipBottom,clipVertical);
        animationValues[0][0]=animatingImageView.getScaleX();
        animationValues[0][1]=animatingImageView.getScaleY();
        animationValues[0][2]=animatingImageView.getTranslationX();
        animationValues[0][3]=animatingImageView.getTranslationY();
        animationValues[0][4]=0;
        animationValues[0][5]=0;
        animationValues[0][6]=0;
        animationValues[0][7]=0;
        animationValues[0][8]=0;
        animationValues[0][9]=0;
        animationValues[1][0]=object.scale;
        animationValues[1][1]=object.scale;
        animationValues[1][2]=object.viewX + drawRegion.left * object.scale;
        animationValues[1][3]=object.viewY + drawRegion.top * object.scale;
        animationValues[1][4]=clipHorizontal * object.scale;
        animationValues[1][5]=clipTop * object.scale;
        animationValues[1][6]=clipBottom * object.scale;
        animationValues[1][7]=object.radius;
        animationValues[1][8]=clipVertical * object.scale;
        animationValues[1][9]=clipHorizontal * object.scale;
        ArrayList<Animator> animators=new ArrayList<>(sendPhotoType == SELECT_TYPE_AVATAR ? 4 : 3);
        animators.add(ObjectAnimator.ofFloat(animatingImageView,AnimationProperties.CLIPPING_IMAGE_VIEW_PROGRESS,0.0f,1.0f));
        animators.add(ObjectAnimator.ofInt(backgroundDrawable,AnimationProperties.COLOR_DRAWABLE_ALPHA,0));
        animators.add(ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f));
        if (sendPhotoType == SELECT_TYPE_AVATAR) {
          animators.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,0.0f));
        }
        animatorSet.playTogether(animators);
      }
 else {
        int h=(AndroidUtilities.displaySize.y + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0));
        animatorSet.playTogether(ObjectAnimator.ofInt(backgroundDrawable,AnimationProperties.COLOR_DRAWABLE_ALPHA,0),ObjectAnimator.ofFloat(animatingImageView,View.ALPHA,0.0f),ObjectAnimator.ofFloat(animatingImageView,View.TRANSLATION_Y,translationY >= 0 ? h : -h),ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f));
      }
      animationEndRunnable=() -> {
        if (Build.VERSION.SDK_INT >= 18) {
          containerView.setLayerType(View.LAYER_TYPE_NONE,null);
        }
        animationInProgress=0;
        onPhotoClosed(object);
      }
;
      animatorSet.setDuration(200);
      animatorSet.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          AndroidUtilities.runOnUIThread(() -> {
            if (animationEndRunnable != null) {
              animationEndRunnable.run();
              animationEndRunnable=null;
            }
          }
);
        }
      }
);
      transitionAnimationStartTime=System.currentTimeMillis();
      if (Build.VERSION.SDK_INT >= 18) {
        containerView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
      }
      animatorSet.start();
    }
 else {
      AnimatorSet animatorSet=new AnimatorSet();
      animatorSet.playTogether(ObjectAnimator.ofFloat(containerView,View.SCALE_X,0.9f),ObjectAnimator.ofFloat(containerView,View.SCALE_Y,0.9f),ObjectAnimator.ofInt(backgroundDrawable,AnimationProperties.COLOR_DRAWABLE_ALPHA,0),ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f));
      animationInProgress=2;
      animationEndRunnable=() -> {
        if (containerView == null) {
          return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
          containerView.setLayerType(View.LAYER_TYPE_NONE,null);
        }
        animationInProgress=0;
        onPhotoClosed(object);
        containerView.setScaleX(1.0f);
        containerView.setScaleY(1.0f);
      }
;
      animatorSet.setDuration(200);
      animatorSet.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (animationEndRunnable != null) {
            animationEndRunnable.run();
            animationEndRunnable=null;
          }
        }
      }
);
      transitionAnimationStartTime=System.currentTimeMillis();
      if (Build.VERSION.SDK_INT >= 18) {
        containerView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
      }
      animatorSet.start();
    }
    if (currentAnimation != null) {
      currentAnimation.setSecondParentView(null);
      currentAnimation=null;
      centerImage.setImageBitmap((Drawable)null);
    }
    if (placeProvider != null && !placeProvider.canScrollAway()) {
      placeProvider.cancelButtonPressed();
    }
  }
}

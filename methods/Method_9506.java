public boolean openPhoto(final MessageObject messageObject,final TLRPC.FileLocation fileLocation,final ArrayList<MessageObject> messages,final ArrayList<SecureDocument> documents,final ArrayList<Object> photos,final int index,final PhotoViewerProvider provider,ChatActivity chatActivity,long dialogId,long mDialogId,boolean fullScreenVideo){
  if (parentActivity == null || isVisible || provider == null && checkAnimation() || messageObject == null && fileLocation == null && messages == null && photos == null && documents == null) {
    return false;
  }
  final PlaceProviderObject object=provider.getPlaceForPhoto(messageObject,fileLocation,index,true);
  if (object == null && photos == null) {
    return false;
  }
  lastInsets=null;
  WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
  if (attachedToWindow) {
    try {
      wm.removeView(windowView);
    }
 catch (    Exception e) {
    }
  }
  try {
    windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
    if (Build.VERSION.SDK_INT >= 21) {
      windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
    }
 else {
      windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
    }
    windowLayoutParams.softInputMode=WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION;
    windowView.setFocusable(false);
    containerView.setFocusable(false);
    wm.addView(windowView,windowLayoutParams);
  }
 catch (  Exception e) {
    FileLog.e(e);
    return false;
  }
  doneButtonPressed=false;
  parentChatActivity=chatActivity;
  actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,1,1));
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.fileDidFailedLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.fileDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.FileLoadProgressChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mediaCountDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mediaDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.dialogPhotosLoaded);
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.emojiDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.filePreparingFailed);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.fileNewChunkAvailable);
  placeProvider=provider;
  mergeDialogId=mDialogId;
  currentDialogId=dialogId;
  selectedPhotosAdapter.notifyDataSetChanged();
  if (velocityTracker == null) {
    velocityTracker=VelocityTracker.obtain();
  }
  isVisible=true;
  togglePhotosListView(false,false);
  openedFullScreenVideo=!fullScreenVideo;
  if (openedFullScreenVideo) {
    toggleActionBar(false,false);
  }
 else {
    if (sendPhotoType == SELECT_TYPE_AVATAR) {
      createCropView();
      toggleActionBar(false,false);
    }
 else {
      toggleActionBar(true,false);
    }
  }
  seekToProgressPending2=0;
  skipFirstBufferingProgress=false;
  playerInjected=false;
  if (object != null) {
    disableShowCheck=true;
    animationInProgress=1;
    if (messageObject != null) {
      currentAnimation=object.imageReceiver.getAnimation();
      if (currentAnimation != null) {
        if (messageObject.isVideo()) {
          object.imageReceiver.setAllowStartAnimation(false);
          object.imageReceiver.stopAnimation();
          if (MediaController.getInstance().isPlayingMessage(messageObject)) {
            seekToProgressPending2=messageObject.audioProgress;
          }
          skipFirstBufferingProgress=injectingVideoPlayer == null && !FileLoader.getInstance(messageObject.currentAccount).isLoadingVideo(messageObject.getDocument(),true) && (currentAnimation.hasBitmap() || !FileLoader.getInstance(messageObject.currentAccount).isLoadingVideo(messageObject.getDocument(),false));
          currentAnimation=null;
        }
 else         if (messageObject.getWebPagePhotos(null,null).size() > 1) {
          currentAnimation=null;
        }
      }
    }
    onPhotoShow(messageObject,fileLocation,messages,documents,photos,index,object);
    if (sendPhotoType == SELECT_TYPE_AVATAR) {
      photoCropView.setVisibility(View.VISIBLE);
      photoCropView.setAlpha(0.0f);
      photoCropView.setFreeform(false);
    }
    windowView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        windowView.getViewTreeObserver().removeOnPreDrawListener(this);
        final RectF drawRegion=object.imageReceiver.getDrawRegion();
        int orientation=object.imageReceiver.getOrientation();
        int animatedOrientation=object.imageReceiver.getAnimatedOrientation();
        if (animatedOrientation != 0) {
          orientation=animatedOrientation;
        }
        animatingImageView.setVisibility(View.VISIBLE);
        animatingImageView.setRadius(object.radius);
        animatingImageView.setOrientation(orientation);
        animatingImageView.setNeedRadius(object.radius != 0);
        animatingImageView.setImageBitmap(object.thumb);
        initCropView();
        if (sendPhotoType == SELECT_TYPE_AVATAR) {
          photoCropView.hideBackView();
          photoCropView.setAspectRatio(1.0f);
        }
        animatingImageView.setAlpha(1.0f);
        animatingImageView.setPivotX(0.0f);
        animatingImageView.setPivotY(0.0f);
        animatingImageView.setScaleX(object.scale);
        animatingImageView.setScaleY(object.scale);
        animatingImageView.setTranslationX(object.viewX + drawRegion.left * object.scale);
        animatingImageView.setTranslationY(object.viewY + drawRegion.top * object.scale);
        final ViewGroup.LayoutParams layoutParams=animatingImageView.getLayoutParams();
        layoutParams.width=(int)drawRegion.width();
        layoutParams.height=(int)drawRegion.height();
        animatingImageView.setLayoutParams(layoutParams);
        float scaleX;
        float scaleY;
        float scale;
        float yPos;
        float xPos;
        if (sendPhotoType == SELECT_TYPE_AVATAR) {
          float statusBarHeight=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
          float measuredHeight=(float)photoCropView.getMeasuredHeight() - AndroidUtilities.dp(64) - statusBarHeight;
          float minSide=Math.min(photoCropView.getMeasuredWidth(),measuredHeight) - 2 * AndroidUtilities.dp(16);
          float centerX=photoCropView.getMeasuredWidth() / 2.0f;
          float centerY=statusBarHeight + measuredHeight / 2.0f;
          float left=centerX - (minSide / 2.0f);
          float top=centerY - (minSide / 2.0f);
          float right=centerX + (minSide / 2.0f);
          float bottom=centerY + (minSide / 2.0f);
          scaleX=(right - left) / layoutParams.width;
          scaleY=(bottom - top) / layoutParams.height;
          scale=Math.max(scaleX,scaleY);
          yPos=top + (bottom - top - layoutParams.height * scale) / 2;
          xPos=(windowView.getMeasuredWidth() - getLeftInset() - getRightInset() - layoutParams.width * scale) / 2.0f + getLeftInset();
        }
 else {
          scaleX=(float)windowView.getMeasuredWidth() / layoutParams.width;
          scaleY=(float)(AndroidUtilities.displaySize.y + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0)) / layoutParams.height;
          scale=scaleX > scaleY ? scaleY : scaleX;
          yPos=((AndroidUtilities.displaySize.y + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0)) - (layoutParams.height * scale)) / 2.0f;
          xPos=(windowView.getMeasuredWidth() - layoutParams.width * scale) / 2.0f;
        }
        int clipHorizontal=(int)Math.abs(drawRegion.left - object.imageReceiver.getImageX());
        int clipVertical=(int)Math.abs(drawRegion.top - object.imageReceiver.getImageY());
        int[] coords2=new int[2];
        object.parentView.getLocationInWindow(coords2);
        int clipTop=(int)(coords2[1] - (Build.VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight) - (object.viewY + drawRegion.top) + object.clipTopAddition);
        if (clipTop < 0) {
          clipTop=0;
        }
        int clipBottom=(int)((object.viewY + drawRegion.top + layoutParams.height) - (coords2[1] + object.parentView.getHeight() - (Build.VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight)) + object.clipBottomAddition);
        if (clipBottom < 0) {
          clipBottom=0;
        }
        clipTop=Math.max(clipTop,clipVertical);
        clipBottom=Math.max(clipBottom,clipVertical);
        animationValues[0][0]=animatingImageView.getScaleX();
        animationValues[0][1]=animatingImageView.getScaleY();
        animationValues[0][2]=animatingImageView.getTranslationX();
        animationValues[0][3]=animatingImageView.getTranslationY();
        animationValues[0][4]=clipHorizontal * object.scale;
        animationValues[0][5]=clipTop * object.scale;
        animationValues[0][6]=clipBottom * object.scale;
        animationValues[0][7]=animatingImageView.getRadius();
        animationValues[0][8]=clipVertical * object.scale;
        animationValues[0][9]=clipHorizontal * object.scale;
        animationValues[1][0]=scale;
        animationValues[1][1]=scale;
        animationValues[1][2]=xPos;
        animationValues[1][3]=yPos;
        animationValues[1][4]=0;
        animationValues[1][5]=0;
        animationValues[1][6]=0;
        animationValues[1][7]=0;
        animationValues[1][8]=0;
        animationValues[1][9]=0;
        animatingImageView.setAnimationProgress(0);
        backgroundDrawable.setAlpha(0);
        containerView.setAlpha(0);
        animationEndRunnable=() -> {
          if (containerView == null || windowView == null) {
            return;
          }
          if (Build.VERSION.SDK_INT >= 18) {
            containerView.setLayerType(View.LAYER_TYPE_NONE,null);
          }
          animationInProgress=0;
          transitionAnimationStartTime=0;
          setImages();
          setCropBitmap();
          if (sendPhotoType == SELECT_TYPE_AVATAR) {
            photoCropView.showBackView();
          }
          containerView.invalidate();
          animatingImageView.setVisibility(View.GONE);
          if (showAfterAnimation != null) {
            showAfterAnimation.imageReceiver.setVisible(true,true);
          }
          if (hideAfterAnimation != null) {
            hideAfterAnimation.imageReceiver.setVisible(false,true);
          }
          if (photos != null && sendPhotoType != 3) {
            if (Build.VERSION.SDK_INT >= 21) {
              windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
            }
 else {
              windowLayoutParams.flags=0;
            }
            windowLayoutParams.softInputMode=WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION;
            WindowManager wm1=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
            wm1.updateViewLayout(windowView,windowLayoutParams);
            windowView.setFocusable(true);
            containerView.setFocusable(true);
          }
        }
;
        if (!openedFullScreenVideo) {
          final AnimatorSet animatorSet=new AnimatorSet();
          ArrayList<Animator> animators=new ArrayList<>(sendPhotoType == SELECT_TYPE_AVATAR ? 4 : 3);
          animators.add(ObjectAnimator.ofFloat(animatingImageView,AnimationProperties.CLIPPING_IMAGE_VIEW_PROGRESS,0.0f,1.0f));
          animators.add(ObjectAnimator.ofInt(backgroundDrawable,AnimationProperties.COLOR_DRAWABLE_ALPHA,0,255));
          animators.add(ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f,1.0f));
          if (sendPhotoType == SELECT_TYPE_AVATAR) {
            animators.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,0,1.0f));
          }
          animatorSet.playTogether(animators);
          animatorSet.setDuration(200);
          animatorSet.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              AndroidUtilities.runOnUIThread(() -> {
                NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
                if (animationEndRunnable != null) {
                  animationEndRunnable.run();
                  animationEndRunnable=null;
                }
              }
);
            }
          }
);
          if (Build.VERSION.SDK_INT >= 18) {
            containerView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
          }
          transitionAnimationStartTime=System.currentTimeMillis();
          AndroidUtilities.runOnUIThread(() -> {
            NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats,NotificationCenter.mediaCountDidLoad,NotificationCenter.mediaDidLoad,NotificationCenter.dialogPhotosLoaded});
            NotificationCenter.getInstance(currentAccount).setAnimationInProgress(true);
            animatorSet.start();
          }
);
        }
 else {
          if (animationEndRunnable != null) {
            animationEndRunnable.run();
            animationEndRunnable=null;
          }
          containerView.setAlpha(1.0f);
          backgroundDrawable.setAlpha(255);
          animatingImageView.setAnimationProgress(1.0f);
          if (sendPhotoType == SELECT_TYPE_AVATAR) {
            photoCropView.setAlpha(1.0f);
          }
        }
        backgroundDrawable.drawRunnable=() -> {
          disableShowCheck=false;
          object.imageReceiver.setVisible(false,true);
        }
;
        return true;
      }
    }
);
  }
 else {
    if (photos != null && sendPhotoType != 3) {
      if (Build.VERSION.SDK_INT >= 21) {
        windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
      }
 else {
        windowLayoutParams.flags=0;
      }
      windowLayoutParams.softInputMode=WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION;
      wm.updateViewLayout(windowView,windowLayoutParams);
      windowView.setFocusable(true);
      containerView.setFocusable(true);
    }
    backgroundDrawable.setAlpha(255);
    containerView.setAlpha(1.0f);
    onPhotoShow(messageObject,fileLocation,messages,documents,photos,index,object);
    initCropView();
    setCropBitmap();
  }
  AccessibilityManager am=(AccessibilityManager)parentActivity.getSystemService(Context.ACCESSIBILITY_SERVICE);
  if (am.isTouchExplorationEnabled()) {
    AccessibilityEvent event=AccessibilityEvent.obtain();
    event.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
    event.getText().add(LocaleController.getString("AccDescrPhotoViewer",R.string.AccDescrPhotoViewer));
    am.sendAccessibilityEvent(event);
  }
  return true;
}

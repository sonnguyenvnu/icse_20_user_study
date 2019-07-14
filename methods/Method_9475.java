private void preparePlayer(Uri uri,boolean playWhenReady,boolean preview){
  if (!preview) {
    currentPlayingVideoFile=uri;
  }
  if (parentActivity == null) {
    return;
  }
  streamingAlertShown=false;
  startedPlayTime=SystemClock.elapsedRealtime();
  currentVideoFinishedLoading=false;
  lastBufferedPositionCheck=0;
  firstAnimationDelay=true;
  inPreview=preview;
  releasePlayer(false);
  if (videoTextureView == null) {
    aspectRatioFrameLayout=new AspectRatioFrameLayout(parentActivity){
      @Override protected void onMeasure(      int widthMeasureSpec,      int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        if (textureImageView != null) {
          ViewGroup.LayoutParams layoutParams=textureImageView.getLayoutParams();
          layoutParams.width=getMeasuredWidth();
          layoutParams.height=getMeasuredHeight();
        }
      }
    }
;
    aspectRatioFrameLayout.setVisibility(View.INVISIBLE);
    containerView.addView(aspectRatioFrameLayout,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
    videoTextureView=new TextureView(parentActivity);
    if (injectingVideoPlayerSurface != null) {
      videoTextureView.setSurfaceTexture(injectingVideoPlayerSurface);
      textureUploaded=true;
      injectingVideoPlayerSurface=null;
    }
    videoTextureView.setPivotX(0);
    videoTextureView.setPivotY(0);
    videoTextureView.setOpaque(false);
    aspectRatioFrameLayout.addView(videoTextureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  }
  if (Build.VERSION.SDK_INT >= 21 && textureImageView == null) {
    textureImageView=new ImageView(parentActivity);
    textureImageView.setBackgroundColor(0xffff0000);
    textureImageView.setPivotX(0);
    textureImageView.setPivotY(0);
    textureImageView.setVisibility(View.INVISIBLE);
    containerView.addView(textureImageView);
  }
  textureUploaded=false;
  videoCrossfadeStarted=false;
  videoTextureView.setAlpha(videoCrossfadeAlpha=0.0f);
  videoPlayButton.setImageResource(R.drawable.inline_video_play);
  boolean newPlayerCreated=false;
  playerWasReady=false;
  if (videoPlayer == null) {
    if (injectingVideoPlayer != null) {
      videoPlayer=injectingVideoPlayer;
      injectingVideoPlayer=null;
      playerInjected=true;
      updatePlayerState(videoPlayer.getPlayWhenReady(),videoPlayer.getPlaybackState());
    }
 else {
      videoPlayer=new VideoPlayer();
      newPlayerCreated=true;
    }
    videoPlayer.setTextureView(videoTextureView);
    videoPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
      @Override public void onStateChanged(      boolean playWhenReady,      int playbackState){
        updatePlayerState(playWhenReady,playbackState);
      }
      @Override public void onError(      Exception e){
        FileLog.e(e);
        if (!menuItem.isSubItemVisible(gallery_menu_openin)) {
          return;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("CantPlayVideo",R.string.CantPlayVideo));
        builder.setPositiveButton(LocaleController.getString("Open",R.string.Open),(dialog,which) -> {
          try {
            AndroidUtilities.openForView(currentMessageObject,parentActivity);
            closePhoto(false,false);
          }
 catch (          Exception e1) {
            FileLog.e(e1);
          }
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        showAlertDialog(builder);
      }
      @Override public void onVideoSizeChanged(      int width,      int height,      int unappliedRotationDegrees,      float pixelWidthHeightRatio){
        if (aspectRatioFrameLayout != null) {
          if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
            int temp=width;
            width=height;
            height=temp;
          }
          aspectRatioFrameLayout.setAspectRatio(height == 0 ? 1 : (width * pixelWidthHeightRatio) / height,unappliedRotationDegrees);
        }
      }
      @Override public void onRenderedFirstFrame(){
        if (!textureUploaded) {
          textureUploaded=true;
          containerView.invalidate();
        }
      }
      @Override public boolean onSurfaceDestroyed(      SurfaceTexture surfaceTexture){
        if (changingTextureView) {
          changingTextureView=false;
          if (isInline) {
            if (isInline) {
              waitingForFirstTextureUpload=1;
            }
            changedTextureView.setSurfaceTexture(surfaceTexture);
            changedTextureView.setSurfaceTextureListener(surfaceTextureListener);
            changedTextureView.setVisibility(View.VISIBLE);
            return true;
          }
        }
        return false;
      }
      @Override public void onSurfaceTextureUpdated(      SurfaceTexture surfaceTexture){
        if (waitingForFirstTextureUpload == 2) {
          if (textureImageView != null) {
            textureImageView.setVisibility(View.INVISIBLE);
            textureImageView.setImageDrawable(null);
            if (currentBitmap != null) {
              currentBitmap.recycle();
              currentBitmap=null;
            }
          }
          switchingInlineMode=false;
          if (Build.VERSION.SDK_INT >= 21) {
            aspectRatioFrameLayout.getLocationInWindow(pipPosition);
            pipPosition[1]-=containerView.getTranslationY();
            textureImageView.setTranslationX(textureImageView.getTranslationX() + getLeftInset());
            videoTextureView.setTranslationX(videoTextureView.getTranslationX() + getLeftInset() - aspectRatioFrameLayout.getX());
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(textureImageView,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(textureImageView,View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(textureImageView,View.TRANSLATION_X,pipPosition[0]),ObjectAnimator.ofFloat(textureImageView,View.TRANSLATION_Y,pipPosition[1]),ObjectAnimator.ofFloat(videoTextureView,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(videoTextureView,View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(videoTextureView,View.TRANSLATION_X,pipPosition[0] - aspectRatioFrameLayout.getX()),ObjectAnimator.ofFloat(videoTextureView,View.TRANSLATION_Y,pipPosition[1] - aspectRatioFrameLayout.getY()),ObjectAnimator.ofInt(backgroundDrawable,AnimationProperties.COLOR_DRAWABLE_ALPHA,255),ObjectAnimator.ofFloat(actionBar,View.ALPHA,1.0f),ObjectAnimator.ofFloat(bottomLayout,View.ALPHA,1.0f),ObjectAnimator.ofFloat(captionTextView,View.ALPHA,1.0f),ObjectAnimator.ofFloat(groupedPhotosListView,View.ALPHA,1.0f));
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.setDuration(250);
            animatorSet.addListener(new AnimatorListenerAdapter(){
              @Override public void onAnimationEnd(              Animator animation){
                pipAnimationInProgress=false;
              }
            }
);
            animatorSet.start();
          }
 else {
          }
          waitingForFirstTextureUpload=0;
        }
      }
    }
);
  }
  if (newPlayerCreated) {
    seekToProgressPending=seekToProgressPending2;
    videoPlayer.preparePlayer(uri,"other");
    videoPlayerSeekbar.setProgress(0);
    videoTimelineView.setProgress(0);
    videoPlayerSeekbar.setBufferedProgress(0);
    videoPlayer.setPlayWhenReady(playWhenReady);
  }
  if (currentBotInlineResult != null && (currentBotInlineResult.type.equals("video") || MessageObject.isVideoDocument(currentBotInlineResult.document))) {
    bottomLayout.setVisibility(View.VISIBLE);
    bottomLayout.setPadding(0,0,AndroidUtilities.dp(84),0);
    pickerView.setVisibility(View.GONE);
  }
 else {
    bottomLayout.setPadding(0,0,0,0);
  }
  videoPlayerControlFrameLayout.setVisibility(isCurrentVideo ? View.GONE : View.VISIBLE);
  dateTextView.setVisibility(View.GONE);
  nameTextView.setVisibility(View.GONE);
  if (allowShare) {
    shareButton.setVisibility(View.GONE);
    menuItem.showSubItem(gallery_menu_share);
  }
  inPreview=preview;
  updateAccessibilityOverlayVisibility();
}

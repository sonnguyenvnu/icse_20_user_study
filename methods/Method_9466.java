public void exitFromPip(){
  if (!isInline) {
    return;
  }
  if (Instance != null) {
    Instance.closePhoto(false,true);
  }
  Instance=PipInstance;
  PipInstance=null;
  switchingInlineMode=true;
  if (currentBitmap != null) {
    currentBitmap.recycle();
    currentBitmap=null;
  }
  changingTextureView=true;
  isInline=false;
  videoTextureView.setVisibility(View.INVISIBLE);
  aspectRatioFrameLayout.addView(videoTextureView);
  if (ApplicationLoader.mainInterfacePaused) {
    try {
      parentActivity.startService(new Intent(ApplicationLoader.applicationContext,BringAppForegroundService.class));
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
  if (Build.VERSION.SDK_INT >= 21) {
    pipAnimationInProgress=true;
    org.telegram.ui.Components.Rect rect=PipVideoView.getPipRect(aspectRatioFrameLayout.getAspectRatio());
    float scale=rect.width / textureImageView.getLayoutParams().width;
    rect.y+=AndroidUtilities.statusBarHeight;
    textureImageView.setScaleX(scale);
    textureImageView.setScaleY(scale);
    textureImageView.setTranslationX(rect.x);
    textureImageView.setTranslationY(rect.y);
    videoTextureView.setScaleX(scale);
    videoTextureView.setScaleY(scale);
    videoTextureView.setTranslationX(rect.x - aspectRatioFrameLayout.getX());
    videoTextureView.setTranslationY(rect.y - aspectRatioFrameLayout.getY());
  }
 else {
    pipVideoView.close();
    pipVideoView=null;
  }
  try {
    isVisible=true;
    WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
    wm.addView(windowView,windowLayoutParams);
    if (currentPlaceObject != null) {
      currentPlaceObject.imageReceiver.setVisible(false,false);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (Build.VERSION.SDK_INT >= 21) {
    waitingForDraw=4;
  }
}

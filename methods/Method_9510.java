public void destroyPhotoViewer(){
  if (parentActivity == null || windowView == null) {
    return;
  }
  if (pipVideoView != null) {
    pipVideoView.close();
    pipVideoView=null;
  }
  removeObservers();
  releasePlayer(false);
  try {
    if (windowView.getParent() != null) {
      WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
      wm.removeViewImmediate(windowView);
    }
    windowView=null;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (currentThumb != null) {
    currentThumb.release();
    currentThumb=null;
  }
  animatingImageView.setImageBitmap(null);
  if (captionEditText != null) {
    captionEditText.onDestroy();
  }
  if (this == PipInstance) {
    PipInstance=null;
  }
 else {
    Instance=null;
  }
}

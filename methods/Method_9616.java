private boolean checkPhotoAnimation(){
  if (photoAnimationInProgress != 0) {
    if (Math.abs(photoTransitionAnimationStartTime - System.currentTimeMillis()) >= 500) {
      if (photoAnimationEndRunnable != null) {
        photoAnimationEndRunnable.run();
        photoAnimationEndRunnable=null;
      }
      photoAnimationInProgress=0;
    }
  }
  return photoAnimationInProgress != 0;
}

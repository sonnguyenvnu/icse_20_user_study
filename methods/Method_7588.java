public void startActivityForResult(final Intent intent,final int requestCode){
  if (parentActivity == null) {
    return;
  }
  if (transitionAnimationInProgress) {
    if (currentAnimation != null) {
      currentAnimation.cancel();
      currentAnimation=null;
    }
    if (onCloseAnimationEndRunnable != null) {
      onCloseAnimationEnd();
    }
 else     if (onOpenAnimationEndRunnable != null) {
      onOpenAnimationEnd();
    }
    containerView.invalidate();
    if (intent != null) {
      parentActivity.startActivityForResult(intent,requestCode);
    }
  }
 else {
    if (intent != null) {
      parentActivity.startActivityForResult(intent,requestCode);
    }
  }
}

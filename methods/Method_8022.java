@Override public void setTranslationX(float value){
  translationX=(int)value;
  if (translationDrawable != null && translationX == 0) {
    if (translationDrawable instanceof LottieDrawable) {
      LottieDrawable lottieDrawable=(LottieDrawable)translationDrawable;
      lottieDrawable.setProgress(0.0f);
    }
    translationAnimationStarted=false;
    archiveHidden=SharedConfig.archiveHidden;
    currentRevealProgress=0;
    isSliding=false;
  }
  if (translationX != 0) {
    isSliding=true;
  }
  if (isSliding) {
    boolean prevValue=drawRevealBackground;
    drawRevealBackground=Math.abs(translationX) >= getMeasuredWidth() * 0.3f;
    if (prevValue != drawRevealBackground && archiveHidden == SharedConfig.archiveHidden) {
      try {
        performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
      }
 catch (      Exception ignore) {
      }
    }
  }
  invalidate();
}

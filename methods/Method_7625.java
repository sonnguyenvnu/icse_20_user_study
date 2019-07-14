private void runShadowAnimation(final int num,final boolean show){
  if (show && !shadowVisibility[num] || !show && shadowVisibility[num]) {
    shadowVisibility[num]=show;
    if (shadowAnimation[num] != null) {
      shadowAnimation[num].cancel();
    }
    shadowAnimation[num]=new AnimatorSet();
    if (shadow[num] != null) {
      shadowAnimation[num].playTogether(ObjectAnimator.ofInt(shadow[num],"alpha",show ? 255 : 0));
    }
    shadowAnimation[num].setDuration(150);
    shadowAnimation[num].addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (shadowAnimation[num] != null && shadowAnimation[num].equals(animation)) {
          shadowAnimation[num]=null;
        }
      }
      @Override public void onAnimationCancel(      Animator animation){
        if (shadowAnimation[num] != null && shadowAnimation[num].equals(animation)) {
          shadowAnimation[num]=null;
        }
      }
    }
);
    try {
      shadowAnimation[num].start();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}

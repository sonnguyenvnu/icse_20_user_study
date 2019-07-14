private void runShadowAnimation(final int num,final boolean show){
  if (show && shadow[num].getTag() != null || !show && shadow[num].getTag() == null) {
    shadow[num].setTag(show ? null : 1);
    if (show) {
      shadow[num].setVisibility(View.VISIBLE);
    }
    if (shadowAnimation[num] != null) {
      shadowAnimation[num].cancel();
    }
    shadowAnimation[num]=new AnimatorSet();
    shadowAnimation[num].playTogether(ObjectAnimator.ofFloat(shadow[num],View.ALPHA,show ? 1.0f : 0.0f));
    shadowAnimation[num].setDuration(150);
    shadowAnimation[num].addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (shadowAnimation[num] != null && shadowAnimation[num].equals(animation)) {
          if (!show) {
            shadow[num].setVisibility(View.INVISIBLE);
          }
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
    shadowAnimation[num].start();
  }
}

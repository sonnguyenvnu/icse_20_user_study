public void hideTopView(final boolean animated){
  if (topView == null || !topViewShowed) {
    return;
  }
  topViewShowed=false;
  needShowTopView=false;
  if (allowShowTopView) {
    if (currentTopViewAnimation != null) {
      currentTopViewAnimation.cancel();
      currentTopViewAnimation=null;
    }
    if (animated) {
      currentTopViewAnimation=new AnimatorSet();
      currentTopViewAnimation.playTogether(ObjectAnimator.ofFloat(topView,View.TRANSLATION_Y,topView.getLayoutParams().height),ObjectAnimator.ofFloat(topLineView,View.ALPHA,0.0f));
      currentTopViewAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (currentTopViewAnimation != null && currentTopViewAnimation.equals(animation)) {
            topView.setVisibility(GONE);
            topLineView.setVisibility(GONE);
            resizeForTopView(false);
            currentTopViewAnimation=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (currentTopViewAnimation != null && currentTopViewAnimation.equals(animation)) {
            currentTopViewAnimation=null;
          }
        }
      }
);
      currentTopViewAnimation.setDuration(200);
      currentTopViewAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
      currentTopViewAnimation.start();
    }
 else {
      topView.setVisibility(GONE);
      topLineView.setVisibility(GONE);
      topLineView.setAlpha(0.0f);
      resizeForTopView(false);
      topView.setTranslationY(topView.getLayoutParams().height);
    }
  }
}

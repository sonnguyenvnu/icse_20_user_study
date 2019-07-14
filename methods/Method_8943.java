public void showTemporary(boolean show){
  if (hideShowAnimation != null) {
    hideShowAnimation.cancel();
  }
  hideShowAnimation=new AnimatorSet();
  hideShowAnimation.playTogether(ObjectAnimator.ofFloat(windowView,View.ALPHA,show ? 1.0f : 0.0f),ObjectAnimator.ofFloat(windowView,View.SCALE_X,show ? 1.0f : 0.8f),ObjectAnimator.ofFloat(windowView,View.SCALE_Y,show ? 1.0f : 0.8f));
  hideShowAnimation.setDuration(150);
  if (decelerateInterpolator == null) {
    decelerateInterpolator=new DecelerateInterpolator();
  }
  hideShowAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animation.equals(hideShowAnimation)) {
        hideShowAnimation=null;
      }
    }
  }
);
  hideShowAnimation.setInterpolator(decelerateInterpolator);
  hideShowAnimation.start();
}

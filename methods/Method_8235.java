private void hideFloatingDateView(boolean animated){
  if (floatingDateView.getTag() != null && !currentFloatingDateOnScreen && (!scrollingFloatingDate || currentFloatingTopIsNotMessage)) {
    floatingDateView.setTag(null);
    if (animated) {
      floatingDateAnimation=new AnimatorSet();
      floatingDateAnimation.setDuration(150);
      floatingDateAnimation.playTogether(ObjectAnimator.ofFloat(floatingDateView,View.ALPHA,0.0f));
      floatingDateAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (animation.equals(floatingDateAnimation)) {
            floatingDateAnimation=null;
          }
        }
      }
);
      floatingDateAnimation.setStartDelay(hideDateDelay);
      floatingDateAnimation.start();
    }
 else {
      if (floatingDateAnimation != null) {
        floatingDateAnimation.cancel();
        floatingDateAnimation=null;
      }
      floatingDateView.setAlpha(0.0f);
    }
    hideDateDelay=500;
  }
}

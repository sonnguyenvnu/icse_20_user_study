private void animateToCheckedState(boolean newCheckedState){
  checkAnimator=ObjectAnimator.ofFloat(this,"progress",newCheckedState ? 1 : 0);
  checkAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animation.equals(checkAnimator)) {
        checkAnimator=null;
      }
    }
  }
);
  checkAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  checkAnimator.setDuration(200);
  checkAnimator.start();
}

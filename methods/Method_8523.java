private void animateToCheckedState(boolean newCheckedState){
  isCheckAnimation=newCheckedState;
  checkAnimator=ObjectAnimator.ofFloat(this,"progress",newCheckedState ? 1 : 0);
  checkAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animation.equals(checkAnimator)) {
        checkAnimator=null;
      }
      if (!isChecked) {
        checkedText=null;
      }
    }
  }
);
  checkAnimator.setDuration(300);
  checkAnimator.start();
}

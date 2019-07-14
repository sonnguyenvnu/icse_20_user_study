private void animateToCheckedState(boolean newCheckedState){
  isCheckAnimation=newCheckedState;
  checkAnimator=ObjectAnimator.ofFloat(this,"progress",newCheckedState ? 1 : 0);
  checkAnimator.setDuration(300);
  checkAnimator.start();
}

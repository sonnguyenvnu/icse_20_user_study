private void animateToCheckedState(boolean newCheckedState){
  checkAnimator=ObjectAnimator.ofFloat(this,"progress",newCheckedState ? 1 : 0);
  checkAnimator.setDuration(200);
  checkAnimator.start();
}

private void animateIcon(boolean newCheckedState){
  iconAnimator=ObjectAnimator.ofFloat(this,"iconProgress",newCheckedState ? 1 : 0);
  iconAnimator.setDuration(250);
  iconAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      iconAnimator=null;
    }
  }
);
  iconAnimator.start();
}

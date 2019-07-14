private void startAnimator(){
  mAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationCancel(    Animator animation){
      mAnimator=null;
    }
    @Override public void onAnimationEnd(    Animator animation){
      mAnimator=null;
    }
  }
);
  mAnimator.start();
}

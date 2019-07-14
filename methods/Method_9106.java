private void showWithAnimation(){
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(windowView,View.ALPHA,0.0f,1.0f),ObjectAnimator.ofFloat(windowView,"scaleX",0.0f,1.0f),ObjectAnimator.ofFloat(windowView,"scaleY",0.0f,1.0f));
  animatorSet.setInterpolator(decelerateInterpolator);
  animatorSet.setDuration(150);
  animatorSet.start();
}

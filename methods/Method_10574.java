private void animationShow(int mDuration){
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(linearLayout,"translationY",1000,0).setDuration(mDuration));
  animatorSet.start();
}

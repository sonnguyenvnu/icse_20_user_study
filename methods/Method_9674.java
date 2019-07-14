private void updateMotionButton(){
  checkBoxView[selectedPattern != null ? 2 : 0].setVisibility(View.VISIBLE);
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(checkBoxView[2],View.ALPHA,selectedPattern != null ? 1.0f : 0.0f),ObjectAnimator.ofFloat(checkBoxView[0],View.ALPHA,selectedPattern != null ? 0.0f : 1.0f));
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      checkBoxView[selectedPattern != null ? 0 : 2].setVisibility(View.INVISIBLE);
    }
  }
);
  animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  animatorSet.setDuration(200);
  animatorSet.start();
}

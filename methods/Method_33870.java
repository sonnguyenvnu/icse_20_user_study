private void smoothScrollTo(int destHeight){
  ValueAnimator animator=ValueAnimator.ofInt(getVisiableHeight(),destHeight);
  animator.setDuration(300).start();
  animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      setVisiableHeight((int)animation.getAnimatedValue());
    }
  }
);
  animator.start();
}

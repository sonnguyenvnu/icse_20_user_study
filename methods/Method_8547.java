public void fill(final RectF targetRect,Animator scaleAnimator,boolean animated){
  if (animated) {
    if (animator != null) {
      animator.cancel();
      animator=null;
    }
    AnimatorSet set=new AnimatorSet();
    animator=set;
    set.setDuration(300);
    Animator animators[]=new Animator[5];
    animators[0]=ObjectAnimator.ofFloat(this,"cropLeft",targetRect.left);
    animators[0].setInterpolator(interpolator);
    animators[1]=ObjectAnimator.ofFloat(this,"cropTop",targetRect.top);
    animators[1].setInterpolator(interpolator);
    animators[2]=ObjectAnimator.ofFloat(this,"cropRight",targetRect.right);
    animators[2].setInterpolator(interpolator);
    animators[3]=ObjectAnimator.ofFloat(this,"cropBottom",targetRect.bottom);
    animators[3].setInterpolator(interpolator);
    animators[4]=scaleAnimator;
    animators[4].setInterpolator(interpolator);
    set.playTogether(animators);
    set.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        setActualRect(targetRect);
        animator=null;
      }
    }
);
    set.start();
  }
 else {
    setActualRect(targetRect);
  }
}

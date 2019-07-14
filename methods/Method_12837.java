private void initListener(){
  mUpdateListener=new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      mAnimatorValue=(float)animation.getAnimatedValue();
      invalidate();
    }
  }
;
  mAnimatorListener=new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
    }
    @Override public void onAnimationEnd(    Animator animation){
      mAnimatorHandler.sendEmptyMessage(0);
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
;
}

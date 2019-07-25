public void start(PointF startPoint,PointF endPoint){
  setPosition(startPoint);
  mTranslateAnimator=ObjectAnimator.ofObject(this,"position",new BezierEvaluator(startPoint,endPoint),startPoint,endPoint);
  mTranslateAnimator.setDuration(450);
  mTranslateAnimator.setInterpolator(new AccelerateInterpolator());
  mAnimatorSet.play(mScaleYAnimator).with(mScaleXAnimator).before(mRotateAnimator);
  mAnimatorSet.play(mRotateAnimator).before(mTranslateAnimator);
  mAnimatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (mListener != null) {
        mListener.animEnd();
      }
      ViewGroup parent=(ViewGroup)ShoppingView.this.getParent();
      if (parent != null && parent instanceof ViewGroup) {
        parent.removeView(ShoppingView.this);
      }
    }
  }
);
  mAnimatorSet.start();
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP) private void move(final boolean toRight){
  final int nextPos=getNextPosition(toRight);
  if (nextPos == -1)   return;
  mSmallCircle=mCircleViews.get(nextPos);
  float smallCircleX=toRight ? mLargeCircle.getX() : mLargeCircle.getX() + mLargeCircle.getWidth() - mSmallCircle.getWidth();
  float largeCircleX=toRight ? mSmallCircle.getX() + mSmallCircle.getWidth() - mLargeCircle.getWidth() : mSmallCircle.getX();
  float outerCircleX=mOuterCircle.getX() + largeCircleX - mLargeCircle.getX();
  PropertyValuesHolder pvhX=PropertyValuesHolder.ofFloat("x",mLargeCircle.getX(),largeCircleX);
  ObjectAnimator largeCircleAnim=ObjectAnimator.ofPropertyValuesHolder(mLargeCircle,pvhX,mPvhScaleX,mPvhScaleY);
  pvhX=PropertyValuesHolder.ofFloat("x",mOuterCircle.getX(),outerCircleX);
  ObjectAnimator outerCircleAnim=ObjectAnimator.ofPropertyValuesHolder(mOuterCircle,pvhX,mPvhScaleX,mPvhScaleY);
  PointF smallCircleCenter=mSmallCircle.getCenter();
  PointF smallCircleEndCenter=new PointF(smallCircleCenter.x - (mSmallCircle.getX() - smallCircleX),smallCircleCenter.y);
  mSmallCirclePath.reset();
  mSmallCirclePath.moveTo(smallCircleCenter.x,smallCircleCenter.y);
  mSmallCirclePath.quadTo(smallCircleCenter.x,smallCircleCenter.y,(smallCircleCenter.x + smallCircleEndCenter.x) / 2,(smallCircleCenter.y + smallCircleEndCenter.y) / 2 + mBezierCurveAnchorDistance);
  mSmallCirclePath.lineTo(smallCircleEndCenter.x,smallCircleEndCenter.y);
  ValueAnimator smallCircleAnim;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    smallCircleAnim=ObjectAnimator.ofObject(mSmallCircle,"center",null,mSmallCirclePath);
  }
 else {
    final PathMeasure pathMeasure=new PathMeasure(mSmallCirclePath,false);
    final float[] point=new float[2];
    smallCircleAnim=ValueAnimator.ofFloat(0.0f,1.0f);
    smallCircleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
      @Override public void onAnimationUpdate(      ValueAnimator animation){
        pathMeasure.getPosTan(pathMeasure.getLength() * animation.getAnimatedFraction(),point,null);
        mSmallCircle.setCenter(new PointF(point[0],point[1]));
      }
    }
);
  }
  mPvhRotation.setFloatValues(0,toRight ? -30f : 30f,0,toRight ? 30f : -30f,0);
  ObjectAnimator otherAnim=ObjectAnimator.ofPropertyValuesHolder(mSmallCircle,mPvhRotation,mPvhScale);
  mAnim=new AnimatorSet();
  mAnim.play(smallCircleAnim).with(otherAnim).with(largeCircleAnim).with(outerCircleAnim);
  mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
  mAnim.setDuration(500);
  mAnim.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
    }
    @Override public void onAnimationEnd(    Animator animation){
      swapCircles(mFocusPosition,nextPos);
      mFocusPosition=nextPos;
      if (mOnMoveListener != null) {
        if (toRight) {
          mOnMoveListener.onMovedToRight();
        }
 else {
          mOnMoveListener.onMovedToLeft();
        }
      }
      if (!mPendingAnimations.isEmpty()) {
        move(mPendingAnimations.removeFirst());
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
  mAnim.start();
}

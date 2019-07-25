/** 
 * ??????????
 */
private void action(StatusType type){
  boolean isHide=currentLines < mLineCount;
  if (type != null) {
    mNeedAnimation=false;
  }
  if (mNeedAnimation) {
    ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
    final boolean finalIsHide=isHide;
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
      @Override public void onAnimationUpdate(      ValueAnimator animation){
        Float value=(Float)animation.getAnimatedValue();
        if (finalIsHide) {
          currentLines=mLimitLines + (int)((mLineCount - mLimitLines) * value);
        }
 else {
          if (mNeedContract)           currentLines=mLimitLines + (int)((mLineCount - mLimitLines) * (1 - value));
        }
        setText(setRealContent(mContent));
      }
    }
);
    valueAnimator.setDuration(100);
    valueAnimator.start();
  }
 else {
    if (isHide) {
      currentLines=mLimitLines + ((mLineCount - mLimitLines));
    }
 else {
      if (mNeedContract)       currentLines=mLimitLines;
    }
    setText(setRealContent(mContent));
  }
}

private void initAnimator(){
  mStartingAnimator=ValueAnimator.ofFloat(0,1).setDuration(defaultDuration);
  mSearchingAnimator=ValueAnimator.ofFloat(0,1).setDuration(defaultDuration);
  mEndingAnimator=ValueAnimator.ofFloat(1,0).setDuration(defaultDuration);
  mStartingAnimator.addUpdateListener(mUpdateListener);
  mSearchingAnimator.addUpdateListener(mUpdateListener);
  mEndingAnimator.addUpdateListener(mUpdateListener);
  mStartingAnimator.addListener(mAnimatorListener);
  mSearchingAnimator.addListener(mAnimatorListener);
  mEndingAnimator.addListener(mAnimatorListener);
}

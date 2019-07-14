private void calcOffset(){
  final View currentTabView=mTabsContainer.getChildAt(this.mCurrentTab);
  mCurrentP.left=currentTabView.getLeft();
  mCurrentP.right=currentTabView.getRight();
  final View lastTabView=mTabsContainer.getChildAt(this.mLastTab);
  mLastP.left=lastTabView.getLeft();
  mLastP.right=lastTabView.getRight();
  if (mLastP.left == mCurrentP.left && mLastP.right == mCurrentP.right) {
    invalidate();
  }
 else {
    mValueAnimator.setObjectValues(mLastP,mCurrentP);
    if (mIndicatorBounceEnable) {
      mValueAnimator.setInterpolator(mInterpolator);
    }
    if (mIndicatorAnimDuration < 0) {
      mIndicatorAnimDuration=mIndicatorBounceEnable ? 500 : 250;
    }
    mValueAnimator.setDuration(mIndicatorAnimDuration);
    mValueAnimator.start();
  }
}

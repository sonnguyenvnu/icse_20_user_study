private void calcIndicatorRect(){
  View currentTabView=mTabsContainer.getChildAt(this.mCurrentTab);
  float left=currentTabView.getLeft();
  float right=currentTabView.getRight();
  mIndicatorRect.left=(int)left;
  mIndicatorRect.right=(int)right;
  if (mIndicatorWidth < 0) {
  }
 else {
    float indicatorLeft=currentTabView.getLeft() + (currentTabView.getWidth() - mIndicatorWidth) / 2;
    mIndicatorRect.left=(int)indicatorLeft;
    mIndicatorRect.right=(int)(mIndicatorRect.left + mIndicatorWidth);
  }
}

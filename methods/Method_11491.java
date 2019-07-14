private void calcIndicatorRect(){
  View currentTabView=mTabsContainer.getChildAt(this.mCurrentTab);
  float left=currentTabView.getLeft();
  float right=currentTabView.getRight();
  mIndicatorRect.left=(int)left;
  mIndicatorRect.right=(int)right;
  if (!mIndicatorAnimEnable) {
    if (mCurrentTab == 0) {
      mRadiusArr[0]=mIndicatorCornerRadius;
      mRadiusArr[1]=mIndicatorCornerRadius;
      mRadiusArr[2]=0;
      mRadiusArr[3]=0;
      mRadiusArr[4]=0;
      mRadiusArr[5]=0;
      mRadiusArr[6]=mIndicatorCornerRadius;
      mRadiusArr[7]=mIndicatorCornerRadius;
    }
 else     if (mCurrentTab == mTabCount - 1) {
      mRadiusArr[0]=0;
      mRadiusArr[1]=0;
      mRadiusArr[2]=mIndicatorCornerRadius;
      mRadiusArr[3]=mIndicatorCornerRadius;
      mRadiusArr[4]=mIndicatorCornerRadius;
      mRadiusArr[5]=mIndicatorCornerRadius;
      mRadiusArr[6]=0;
      mRadiusArr[7]=0;
    }
 else {
      mRadiusArr[0]=0;
      mRadiusArr[1]=0;
      mRadiusArr[2]=0;
      mRadiusArr[3]=0;
      mRadiusArr[4]=0;
      mRadiusArr[5]=0;
      mRadiusArr[6]=0;
      mRadiusArr[7]=0;
    }
  }
 else {
    mRadiusArr[0]=mIndicatorCornerRadius;
    mRadiusArr[1]=mIndicatorCornerRadius;
    mRadiusArr[2]=mIndicatorCornerRadius;
    mRadiusArr[3]=mIndicatorCornerRadius;
    mRadiusArr[4]=mIndicatorCornerRadius;
    mRadiusArr[5]=mIndicatorCornerRadius;
    mRadiusArr[6]=mIndicatorCornerRadius;
    mRadiusArr[7]=mIndicatorCornerRadius;
  }
}

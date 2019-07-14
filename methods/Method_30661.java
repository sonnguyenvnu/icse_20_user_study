@Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
  if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
    heightMeasureSpec=MeasureSpec.makeMeasureSpec(mMaxHeight,MeasureSpec.EXACTLY);
  }
  int width=MeasureSpec.getSize(widthMeasureSpec);
  int height=MeasureSpec.getSize(heightMeasureSpec);
  int dismissViewHeight=height - computeVisibleAppBarHeight();
  mDismissView.getLayoutParams().height=dismissViewHeight;
  MarginLayoutParams appBarLayoutLayoutParams=(MarginLayoutParams)mAppBarLayout.getLayoutParams();
  appBarLayoutLayoutParams.topMargin=dismissViewHeight;
  appBarLayoutLayoutParams.height=getAppBarMaxHeight();
  int appBarWidth=ProfileUtils.getAppBarWidth(width,getContext());
  if (mUseWideLayout) {
    mAppBarLayout.setPadding(mAppBarLayout.getPaddingLeft(),mAppBarLayout.getPaddingTop(),width - appBarWidth,mAppBarLayout.getPaddingBottom());
  }
  int largeAvatarSizeHalf=mLargeAvatarSize / 2;
  int avatarMarginTop=dismissViewHeight - mInsetTop - largeAvatarSizeHalf;
  int smallAvatarMarginTop=(mToolbarHeight - mSmallAvatarSize) / 2;
  float avatarHorizontalFraction=avatarMarginTop < smallAvatarMarginTop ? MathUtils.unlerp(smallAvatarMarginTop,-largeAvatarSizeHalf,avatarMarginTop) : 0;
  avatarMarginTop=Math.max(smallAvatarMarginTop,avatarMarginTop) + mInsetTop;
  int avatarMarginLeft=MathUtils.lerpInt(appBarWidth / 2 - largeAvatarSizeHalf,mScreenEdgeHorizontalMargin,avatarHorizontalFraction);
  MarginLayoutParams avatarContainerLayoutParams=(MarginLayoutParams)mAvatarContainerLayout.getLayoutParams();
  avatarContainerLayoutParams.leftMargin=avatarMarginLeft;
  avatarContainerLayoutParams.topMargin=avatarMarginTop;
  float avatarScale=MathUtils.lerp(1,(float)mSmallAvatarSize / mLargeAvatarSize,avatarHorizontalFraction);
  mAvatarContainerLayout.setPivotX(0);
  mAvatarContainerLayout.setPivotY(0);
  mAvatarContainerLayout.setScaleX(avatarScale);
  mAvatarContainerLayout.setScaleY(avatarScale);
  for (int i=0, count=mAppBarLayout.getChildCount(); i < count; ++i) {
    View child=mAppBarLayout.getChildAt(i);
    if (child != mToolbar) {
      child.setAlpha(Math.max(0,1 - getFraction() * 2));
    }
  }
  mToolbarUsernameText.setAlpha(Math.max(0,getFraction() * 2 - 1));
  super.onMeasure(widthMeasureSpec,heightMeasureSpec);
}

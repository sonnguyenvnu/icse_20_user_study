private void measureChild(int widthMeasureSpec,int heightMeasureSpec){
  int maxWidth=0;
  mTotalLength=0;
  mTotalLength+=getPaddingTop() + getPaddingBottom();
  for (int i=0; i < getChildCount(); i++) {
    final View child=getChildAt(i);
    measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
    final int totalLength=mTotalLength;
    final LayoutParams lp=(LayoutParams)child.getLayoutParams();
    if (lp.mHeaderHeight == -1) {
      lp.mHeaderHeight=child.getMeasuredHeight();
    }
    final int childHeight=lp.mHeaderHeight;
    mTotalLength=Math.max(totalLength,totalLength + childHeight + lp.topMargin + lp.bottomMargin);
    mTotalLength-=mOverlapGaps * 2;
    final int margin=lp.leftMargin + lp.rightMargin;
    final int measuredWidth=child.getMeasuredWidth() + margin;
    maxWidth=Math.max(maxWidth,measuredWidth);
  }
  mTotalLength+=mOverlapGaps * 2;
  int heightSize=mTotalLength;
  heightSize=Math.max(heightSize,mShowHeight);
  int heightSizeAndState=resolveSizeAndState(heightSize,heightMeasureSpec,0);
  setMeasuredDimension(resolveSizeAndState(maxWidth,widthMeasureSpec,0),heightSizeAndState);
}

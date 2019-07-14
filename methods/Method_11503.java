private void calcIndicatorRect(){
  View currentTabView=mTabsContainer.getChildAt(this.mCurrentTab);
  float left=currentTabView.getLeft();
  float right=currentTabView.getRight();
  if (mIndicatorStyle == STYLE_NORMAL && mIndicatorWidthEqualTitle) {
    TextView tab_title=(TextView)currentTabView.findViewById(R.id.tv_tab_title);
    mTextPaint.setTextSize(mTextsize);
    float textWidth=mTextPaint.measureText(tab_title.getText().toString());
    margin=(right - left - textWidth) / 2;
  }
  if (this.mCurrentTab < mTabCount - 1) {
    View nextTabView=mTabsContainer.getChildAt(this.mCurrentTab + 1);
    float nextTabLeft=nextTabView.getLeft();
    float nextTabRight=nextTabView.getRight();
    left=left + mCurrentPositionOffset * (nextTabLeft - left);
    right=right + mCurrentPositionOffset * (nextTabRight - right);
    if (mIndicatorStyle == STYLE_NORMAL && mIndicatorWidthEqualTitle) {
      TextView next_tab_title=(TextView)nextTabView.findViewById(R.id.tv_tab_title);
      mTextPaint.setTextSize(mTextsize);
      float nextTextWidth=mTextPaint.measureText(next_tab_title.getText().toString());
      float nextMargin=(nextTabRight - nextTabLeft - nextTextWidth) / 2;
      margin=margin + mCurrentPositionOffset * (nextMargin - margin);
    }
  }
  mIndicatorRect.left=(int)left;
  mIndicatorRect.right=(int)right;
  if (mIndicatorStyle == STYLE_NORMAL && mIndicatorWidthEqualTitle) {
    mIndicatorRect.left=(int)(left + margin - 1);
    mIndicatorRect.right=(int)(right - margin - 1);
  }
  mTabRect.left=(int)left;
  mTabRect.right=(int)right;
  if (mIndicatorWidth < 0) {
  }
 else {
    float indicatorLeft=currentTabView.getLeft() + (currentTabView.getWidth() - mIndicatorWidth) / 2;
    if (this.mCurrentTab < mTabCount - 1) {
      View nextTab=mTabsContainer.getChildAt(this.mCurrentTab + 1);
      indicatorLeft=indicatorLeft + mCurrentPositionOffset * (currentTabView.getWidth() / 2 + nextTab.getWidth() / 2);
    }
    mIndicatorRect.left=(int)indicatorLeft;
    mIndicatorRect.right=(int)(mIndicatorRect.left + mIndicatorWidth);
  }
}

private void scrollDownOneLine(){
  mScrollCounter++;
  if (mLeftMargin != 0 || mRightMargin != mColumns) {
    mScreen.blockCopy(mLeftMargin,mTopMargin + 1,mRightMargin - mLeftMargin,mBottomMargin - mTopMargin - 1,mLeftMargin,mTopMargin);
    mScreen.blockSet(mLeftMargin,mBottomMargin - 1,mRightMargin - mLeftMargin,1,' ',mEffect);
  }
 else {
    mScreen.scrollDownOneLine(mTopMargin,mBottomMargin,getStyle());
  }
}

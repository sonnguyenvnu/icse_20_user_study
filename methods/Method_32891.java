private int nextTabStop(int numTabs){
  for (int i=mCursorCol + 1; i < mColumns; i++)   if (mTabStop[i] && --numTabs == 0)   return Math.min(i,mRightMargin);
  return mRightMargin - 1;
}

public void setMaxSelectableIndex(int maxSelectableIndex){
  if (maxSelectableIndex < mMinSelectableIndex) {
    maxSelectableIndex=mMinSelectableIndex;
  }
  mMaxSelectableIndex=maxSelectableIndex;
  int afterCenter=safeCenter(mCenterIndex);
  if (afterCenter != mCenterIndex) {
    selectIndex(afterCenter);
  }
}

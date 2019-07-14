private void decrementSelectorIndices(int[] selectorIndices){
  System.arraycopy(selectorIndices,0,selectorIndices,1,selectorIndices.length - 1);
  int nextScrollSelectorIndex=selectorIndices[1] - 1;
  if (mWrapSelectorWheel && nextScrollSelectorIndex < mMinValue) {
    nextScrollSelectorIndex=mMaxValue;
  }
  selectorIndices[0]=nextScrollSelectorIndex;
  ensureCachedScrollSelectorValue(nextScrollSelectorIndex);
}

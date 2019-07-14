private void incrementSelectorIndices(int[] selectorIndices){
  System.arraycopy(selectorIndices,1,selectorIndices,0,selectorIndices.length - 1);
  int nextScrollSelectorIndex=selectorIndices[selectorIndices.length - 2] + 1;
  if (mWrapSelectorWheel && nextScrollSelectorIndex > mMaxValue) {
    nextScrollSelectorIndex=mMinValue;
  }
  selectorIndices[selectorIndices.length - 1]=nextScrollSelectorIndex;
  ensureCachedScrollSelectorValue(nextScrollSelectorIndex);
}

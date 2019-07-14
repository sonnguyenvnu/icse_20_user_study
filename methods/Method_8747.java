private void initializeSelectorWheelIndices(){
  mSelectorIndexToStringCache.clear();
  int[] selectorIndices=mSelectorIndices;
  int current=getValue();
  for (int i=0; i < mSelectorIndices.length; i++) {
    int selectorIndex=current + (i - SELECTOR_MIDDLE_ITEM_INDEX);
    if (mWrapSelectorWheel) {
      selectorIndex=getWrappedSelectorIndex(selectorIndex);
    }
    selectorIndices[i]=selectorIndex;
    ensureCachedScrollSelectorValue(selectorIndices[i]);
  }
}

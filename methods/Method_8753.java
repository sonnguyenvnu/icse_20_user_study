private void ensureCachedScrollSelectorValue(int selectorIndex){
  SparseArray<String> cache=mSelectorIndexToStringCache;
  String scrollSelectorValue=cache.get(selectorIndex);
  if (scrollSelectorValue != null) {
    return;
  }
  if (selectorIndex < mMinValue || selectorIndex > mMaxValue) {
    scrollSelectorValue="";
  }
 else {
    if (mDisplayedValues != null) {
      int displayedValueIndex=selectorIndex - mMinValue;
      scrollSelectorValue=mDisplayedValues[displayedValueIndex];
    }
 else {
      scrollSelectorValue=formatNumber(selectorIndex);
    }
  }
  cache.put(selectorIndex,scrollSelectorValue);
}

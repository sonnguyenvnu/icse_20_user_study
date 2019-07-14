@Override protected int computeVerticalScrollRange(){
  return (mMaxValue - mMinValue + 1) * mSelectorElementHeight;
}

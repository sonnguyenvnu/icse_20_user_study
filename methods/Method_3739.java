public View getHiddenChildAt(int index){
  if (index < 0 || index >= mHiddenViews.size()) {
    return null;
  }
  return mHiddenViews.get(index);
}

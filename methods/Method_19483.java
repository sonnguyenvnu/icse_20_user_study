@UiThread boolean removeAffectsVisibleRange(int position,int size){
  if (shouldUpdate()) {
    return true;
  }
  return position <= mCurrentLastVisiblePosition;
}

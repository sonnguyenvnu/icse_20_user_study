@UiThread boolean updateAffectsVisibleRange(int position,int size){
  if (shouldUpdate()) {
    return true;
  }
  for (int index=position; index < position + size; ++index) {
    if (mCurrentFirstVisiblePosition <= index && index <= mCurrentLastVisiblePosition) {
      return true;
    }
  }
  return false;
}

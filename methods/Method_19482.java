@UiThread boolean moveAffectsVisibleRange(int fromPosition,int toPosition,int viewportCount){
  if (shouldUpdate() || viewportCount == RecyclerBinder.UNSET) {
    return true;
  }
  final boolean isNewPositionInVisibleRange=toPosition >= mCurrentFirstVisiblePosition && toPosition <= mCurrentFirstVisiblePosition + viewportCount - 1;
  final boolean isOldPositionInVisibleRange=fromPosition >= mCurrentFirstVisiblePosition && fromPosition <= mCurrentFirstVisiblePosition + viewportCount - 1;
  return isNewPositionInVisibleRange || isOldPositionInVisibleRange;
}

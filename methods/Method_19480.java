@UiThread boolean insertAffectsVisibleRange(int position,int size,int viewportCount){
  if (shouldUpdate() || viewportCount == RecyclerBinder.UNSET) {
    return true;
  }
  final int lastVisiblePosition=Math.max(mCurrentFirstVisiblePosition + viewportCount - 1,mCurrentLastVisiblePosition);
  return position <= lastVisiblePosition;
}

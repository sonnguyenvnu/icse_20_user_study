@VisibleForTesting(otherwise=VisibleForTesting.PRIVATE) int findStickyHeaderPosition(int currentFirstVisiblePosition){
  for (int i=currentFirstVisiblePosition; i >= 0; i--) {
    if (mHasStickyHeader.isSticky(i)) {
      return i;
    }
  }
  return RecyclerView.NO_POSITION;
}

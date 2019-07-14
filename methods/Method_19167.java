public void updateFirstLastFullyVisibleItemPositions(RecyclerView.LayoutManager layoutManager){
  final int firstCompletelyVisibleItemPosition=getFirstCompletelyVisibleItemPosition(layoutManager);
  if (firstCompletelyVisibleItemPosition != -1) {
    mFirstCompletelyVisibleItemPosition=firstCompletelyVisibleItemPosition;
  }
  final int lastCompletelyVisibleItemPosition=getLastCompletelyVisibleItemPosition(layoutManager);
  if (lastCompletelyVisibleItemPosition != -1) {
    mLastCompletelyVisibleItemPosition=lastCompletelyVisibleItemPosition;
  }
}

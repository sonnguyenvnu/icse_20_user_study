@Override View findReferenceChild(RecyclerView.Recycler recycler,RecyclerView.State state,int start,int end,int itemCount){
  ensureLayoutState();
  View invalidMatch=null;
  View outOfBoundsMatch=null;
  final int boundsStart=mOrientationHelper.getStartAfterPadding();
  final int boundsEnd=mOrientationHelper.getEndAfterPadding();
  final int diff=end > start ? 1 : -1;
  for (int i=start; i != end; i+=diff) {
    final View view=getChildAt(i);
    final int position=getPosition(view);
    if (position >= 0 && position < itemCount) {
      final int span=getSpanIndex(recycler,state,position);
      if (span != 0) {
        continue;
      }
      if (((RecyclerView.LayoutParams)view.getLayoutParams()).isItemRemoved()) {
        if (invalidMatch == null) {
          invalidMatch=view;
        }
      }
 else       if (mOrientationHelper.getDecoratedStart(view) >= boundsEnd || mOrientationHelper.getDecoratedEnd(view) < boundsStart) {
        if (outOfBoundsMatch == null) {
          outOfBoundsMatch=view;
        }
      }
 else {
        return view;
      }
    }
  }
  return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
}

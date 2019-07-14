public int calculateDxToMakeVisible(View view){
  final RecyclerView.LayoutManager layoutManager=getLayoutManager();
  if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
    return 0;
  }
  final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)view.getLayoutParams();
  int left=layoutManager.getDecoratedLeft(view) - params.leftMargin;
  int rigth=layoutManager.getDecoratedRight(view) + params.rightMargin;
  int start=layoutManager.getPaddingLeft();
  int end=layoutManager.getWidth() - layoutManager.getPaddingRight();
  if (left > start && rigth < end) {
    return 0;
  }
  int boxSize=end - start;
  int viewSize=rigth - left;
  start=boxSize - viewSize;
  end=start + viewSize;
  final int dtStart=start - left;
  if (dtStart > 0) {
    return dtStart;
  }
  final int dtEnd=end - rigth;
  if (dtEnd < 0) {
    return dtEnd;
  }
  return 0;
}

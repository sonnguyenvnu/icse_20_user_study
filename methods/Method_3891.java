public int calculateDyToMakeVisible(View view){
  final RecyclerView.LayoutManager layoutManager=getLayoutManager();
  if (layoutManager == null || !layoutManager.canScrollVertically()) {
    return 0;
  }
  final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)view.getLayoutParams();
  int top=layoutManager.getDecoratedTop(view) - params.topMargin;
  int bottom=layoutManager.getDecoratedBottom(view) + params.bottomMargin;
  int start=layoutManager.getPaddingTop();
  int end=layoutManager.getHeight() - layoutManager.getPaddingBottom();
  int boxSize=end - start;
  int viewSize=bottom - top;
  if (viewSize > boxSize) {
    start=0;
  }
 else {
    start=(boxSize - viewSize) / 2;
  }
  end=start + viewSize;
  final int dtStart=start - top;
  if (dtStart > 0) {
    return dtStart;
  }
  final int dtEnd=end - bottom;
  if (dtEnd < 0) {
    return dtEnd;
  }
  return 0;
}

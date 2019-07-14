@Override public int computeWrappedHeight(int maxHeight,List<ComponentTreeHolder> componentTreeHolders){
  final int itemCount=componentTreeHolders.size();
  final int spanCount=mStaggeredGridLayoutManager.getSpanCount();
  int measuredHeight=0;
switch (mStaggeredGridLayoutManager.getOrientation()) {
case StaggeredGridLayoutManager.VERTICAL:
    for (int i=0; i < itemCount; i+=spanCount) {
      measuredHeight+=LayoutInfoUtils.getMaxHeightInRow(i,i + spanCount,componentTreeHolders);
      measuredHeight+=LayoutInfoUtils.getTopDecorationHeight(mStaggeredGridLayoutManager,i);
      measuredHeight+=LayoutInfoUtils.getBottomDecorationHeight(mStaggeredGridLayoutManager,i);
      if (measuredHeight > maxHeight) {
        measuredHeight=maxHeight;
        break;
      }
    }
  return measuredHeight;
case StaggeredGridLayoutManager.HORIZONTAL:
default :
throw new IllegalStateException("This method should only be called when orientation is vertical");
}
}

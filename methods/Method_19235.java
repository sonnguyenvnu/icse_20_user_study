@Override public int computeWrappedHeight(int maxHeight,List<ComponentTreeHolder> componentTreeHolders){
  final int itemCount=componentTreeHolders.size();
  final int spanCount=mGridLayoutManager.getSpanCount();
  int measuredHeight=0;
switch (mGridLayoutManager.getOrientation()) {
case GridLayoutManager.VERTICAL:
    for (int i=0; i < itemCount; i+=spanCount) {
      final ComponentTreeHolder holder=componentTreeHolders.get(i);
      int firstRowItemHeight=holder.getMeasuredHeight();
      measuredHeight+=firstRowItemHeight;
      measuredHeight+=LayoutInfoUtils.getTopDecorationHeight(mGridLayoutManager,i);
      measuredHeight+=LayoutInfoUtils.getBottomDecorationHeight(mGridLayoutManager,i);
      if (measuredHeight > maxHeight) {
        measuredHeight=maxHeight;
        break;
      }
    }
  return measuredHeight;
case GridLayoutManager.HORIZONTAL:
default :
throw new IllegalStateException("This method should only be called when orientation is vertical");
}
}

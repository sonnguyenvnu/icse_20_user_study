@Nullable @Override public View findSnapView(LayoutManager layoutManager){
  if (layoutManager.canScrollVertically()) {
    return findViewClosestToStart(layoutManager,getVerticalHelper(layoutManager));
  }
 else   if (layoutManager.canScrollHorizontally()) {
    return findViewClosestToStart(layoutManager,getHorizontalHelper(layoutManager));
  }
  return null;
}

@Nullable @Override public View findSnapView(RecyclerView.LayoutManager layoutManager){
  if (layoutManager.canScrollVertically()) {
    return findCenterView(layoutManager,getVerticalHelper(layoutManager));
  }
 else   if (layoutManager.canScrollHorizontally()) {
    return findCenterView(layoutManager,getHorizontalHelper(layoutManager));
  }
  return null;
}

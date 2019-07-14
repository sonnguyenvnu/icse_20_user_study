@Nullable private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager){
  if (layoutManager.canScrollVertically()) {
    return getVerticalHelper(layoutManager);
  }
 else   if (layoutManager.canScrollHorizontally()) {
    return getHorizontalHelper(layoutManager);
  }
 else {
    return null;
  }
}

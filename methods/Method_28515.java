private boolean canAddDivider(){
  if (getLayoutManager() != null) {
    if (getLayoutManager() instanceof GridLayoutManager) {
      return ((GridLayoutManager)getLayoutManager()).getSpanCount() == 1;
    }
 else     if (getLayoutManager() instanceof LinearLayoutManager) {
      return true;
    }
 else     if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
      return ((StaggeredGridLayoutManager)getLayoutManager()).getSpanCount() == 1;
    }
  }
  return false;
}

private void showParentOrSelf(boolean showRecyclerView){
  if (parentView != null)   parentView.setVisibility(VISIBLE);
  setVisibility(VISIBLE);
  emptyView.setVisibility(!showRecyclerView ? VISIBLE : GONE);
}

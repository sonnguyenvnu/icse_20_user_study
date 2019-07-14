private void showEmptyView(){
  Adapter<?> adapter=getAdapter();
  if (adapter != null) {
    if (emptyView != null) {
      if (adapter.getItemCount() == 0) {
        showParentOrSelf(false);
      }
 else {
        showParentOrSelf(true);
      }
    }
  }
 else {
    if (emptyView != null) {
      showParentOrSelf(false);
    }
  }
}

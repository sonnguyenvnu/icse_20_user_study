private void checkIfEmpty(){
  if (getAdapter() == null || emptyView == null) {
    if (hiddenByEmptyView && getVisibility() != VISIBLE) {
      setVisibility(VISIBLE);
      hiddenByEmptyView=false;
    }
    return;
  }
  boolean emptyViewVisible=getAdapter().getItemCount() == 0;
  emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
  setVisibility(emptyViewVisible ? INVISIBLE : VISIBLE);
  hiddenByEmptyView=true;
}

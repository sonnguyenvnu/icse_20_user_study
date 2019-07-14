private void updateHasContentStateItem(){
  int count=getItemCount() - mContentStateAdapter.getItemCount();
  mContentStateAdapter.setHasItem(count > 0);
}

private void toggleEmptyViewVisibility(){
  emptyView.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
}

protected void onDataSetChanged(){
  if (mAdapter == null) {
    return;
  }
  removeAllViews();
  for (int position=0, count=mAdapter.getItemCount(); position < count; ++position) {
    addItemView(position);
  }
}

protected void onItemRemoved(int position){
  mItemAdapter.remove(position);
  onListUpdated(mResource.get());
}

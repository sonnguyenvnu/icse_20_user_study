protected void onItemChanged(int position,T newItem){
  mItemAdapter.set(position,newItem);
  onListUpdated(mResource.get());
}

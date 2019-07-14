protected void onListChanged(List<T> newList){
  mItemAdapter.replace(newList);
  onListUpdated(mResource.get());
}

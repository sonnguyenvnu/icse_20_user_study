protected void onListAppended(List<T> appendedList){
  mItemAdapter.addAll(appendedList);
  onListUpdated(mResource.get());
}

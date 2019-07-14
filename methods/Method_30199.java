@Subscribe(threadMode=ThreadMode.POSTING) public void onItemCollectionUpdated(ItemCollectionUpdatedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<SimpleItemCollection> itemCollectionList=get();
  for (int i=0, size=itemCollectionList.size(); i < size; ++i) {
    SimpleItemCollection itemCollection=itemCollectionList.get(i);
    if (itemCollection.id == event.itemCollection.id) {
      itemCollectionList.set(i,event.itemCollection);
      getListener().onItemCollectionListItemChanged(getRequestCode(),i,itemCollectionList.get(i));
    }
  }
}

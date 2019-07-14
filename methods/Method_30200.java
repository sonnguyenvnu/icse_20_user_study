@Subscribe(threadMode=ThreadMode.POSTING) public void onItemCollectionWriteStarted(ItemCollectionWriteStartedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<SimpleItemCollection> itemCollectionList=get();
  for (int i=0, size=itemCollectionList.size(); i < size; ++i) {
    SimpleItemCollection itemCollection=itemCollectionList.get(i);
    if (itemCollection.id == event.itemCollectionId) {
      getListener().onItemCollectionListItemWriteStarted(getRequestCode(),i);
    }
  }
}

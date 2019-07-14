@Subscribe(threadMode=ThreadMode.POSTING) public void onItemCollectionWriteFinished(ItemCollectionWriteFinishedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<SimpleItemCollection> itemCollectionList=get();
  for (int i=0, size=itemCollectionList.size(); i < size; ++i) {
    SimpleItemCollection itemCollection=itemCollectionList.get(i);
    if (itemCollection.id == event.itemCollectionId) {
      getListener().onItemCollectionListItemWriteFinished(getRequestCode(),i);
    }
  }
}

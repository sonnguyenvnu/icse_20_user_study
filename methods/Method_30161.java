@Subscribe(threadMode=ThreadMode.POSTING) public void onItemCollected(ItemCollectedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  ItemType item=get();
  if (event.itemType == item.getType() && event.itemId == item.id) {
    item.collection=event.collection;
    getListener().onItemChanged(getRequestCode(),item);
    getListener().onItemCollectionChanged(getRequestCode());
  }
}

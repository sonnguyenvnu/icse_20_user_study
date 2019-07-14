@Subscribe(threadMode=ThreadMode.POSTING) public void onItemUncollected(ItemUncollectedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  ItemType item=get();
  if (event.itemType == item.getType() && event.itemId == item.id) {
    item.collection=null;
    getListener().onItemChanged(getRequestCode(),item);
    getListener().onItemCollectionChanged(getRequestCode());
  }
}

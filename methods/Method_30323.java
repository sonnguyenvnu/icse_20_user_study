@Subscribe(threadMode=ThreadMode.POSTING) public void onItemCollectError(ItemCollectErrorEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.itemType == mItem.getType() && event.itemId == mItem.id) {
    updateCollectStatus();
  }
}

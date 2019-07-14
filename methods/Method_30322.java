@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastSent(ItemCollectedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.itemType == mItem.getType() && event.itemId == mItem.id) {
    mCollected=true;
    finish();
  }
}

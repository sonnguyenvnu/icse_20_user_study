@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastUpdated(BroadcastUpdatedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  Broadcast updatedBroadcast=event.update(mBroadcastId,mBroadcast,this);
  if (updatedBroadcast != null) {
    mBroadcast=updatedBroadcast;
  }
}

@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastDeleted(BroadcastDeletedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.broadcastId == mBroadcastId) {
    set(null);
    getListener().onBroadcastRemoved(getRequestCode());
  }
 else   if (has()) {
    Broadcast broadcast=get();
    if (broadcast.isParentBroadcastId(event.broadcastId)) {
      broadcast.parentBroadcast=null;
      broadcast.parentBroadcastId=null;
      getListener().onBroadcastChanged(getRequestCode(),broadcast);
    }
 else     if (broadcast.rebroadcastedBroadcast != null && broadcast.rebroadcastedBroadcast.id == event.broadcastId) {
      broadcast.rebroadcastedBroadcast.isDeleted=true;
      getListener().onBroadcastChanged(getRequestCode(),broadcast);
    }
  }
}

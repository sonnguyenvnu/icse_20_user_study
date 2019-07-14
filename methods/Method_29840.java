@Override public void onResponse(Void response){
  ToastUtils.show(mBroadcast != null && mBroadcast.isSimpleRebroadcast() ? R.string.broadcast_unrebroadcast_successful : R.string.broadcast_delete_successful,getContext());
  if (mBroadcast != null) {
    Broadcast rebroadcastedBroadcast;
    if (mBroadcast.parentBroadcast != null) {
      rebroadcastedBroadcast=mBroadcast.parentBroadcast;
    }
 else     if (mBroadcast.getParentBroadcastId() != null) {
      rebroadcastedBroadcast=null;
    }
 else {
      rebroadcastedBroadcast=mBroadcast.rebroadcastedBroadcast;
    }
    if (rebroadcastedBroadcast != null) {
      --rebroadcastedBroadcast.rebroadcastCount;
      EventBusUtils.postAsync(new BroadcastUpdatedEvent(rebroadcastedBroadcast,this));
    }
  }
  EventBusUtils.postAsync(new BroadcastDeletedEvent(mBroadcastId,this));
  stopSelf();
}

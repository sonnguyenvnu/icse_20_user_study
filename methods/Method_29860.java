@Override public void onResponse(Broadcast response){
  ToastUtils.show(R.string.broadcast_rebroadcast_successful,getContext());
  EventBusUtils.postAsync(new BroadcastRebroadcastedEvent(mBroadcastId,response,this));
  if (response.parentBroadcast != null) {
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(response.parentBroadcast,this));
  }
  EventBusUtils.postAsync(new BroadcastUpdatedEvent(response.rebroadcastedBroadcast,this));
  stopSelf();
}

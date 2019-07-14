@SuppressWarnings("deprecation") private void mergeAndRepost(Broadcast oldBroadcast,Object source){
  boolean changed=false;
  if (broadcast.parentBroadcast == null && oldBroadcast.parentBroadcast != null) {
    broadcast.parentBroadcast=oldBroadcast.parentBroadcast;
    broadcast.parentBroadcastId=null;
    changed=true;
  }
  if (broadcast.rebroadcastedBroadcast == null && oldBroadcast.rebroadcastedBroadcast != null) {
    broadcast.rebroadcastedBroadcast=oldBroadcast.rebroadcastedBroadcast;
    changed=true;
  }
  if (changed) {
    EventBusUtils.cancel(this);
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(this.broadcast,source));
  }
}

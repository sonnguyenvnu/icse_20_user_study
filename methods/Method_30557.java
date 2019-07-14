public void fix(){
  fixSelf();
  if (parentBroadcast != null) {
    parentBroadcast.fixSelf();
    if (parentBroadcast.parentBroadcastId != null && rebroadcastedBroadcast != null && parentBroadcast.parentBroadcastId == rebroadcastedBroadcast.id) {
      parentBroadcast.parentBroadcastId=null;
    }
  }
  if (rebroadcastedBroadcast != null) {
    rebroadcastedBroadcast.fixSelf();
  }
}

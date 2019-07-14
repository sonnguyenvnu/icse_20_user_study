public boolean isEffectiveBroadcastId(long broadcastId){
  return hasEffectiveBroadcast() && getEffectiveBroadcastId() == broadcastId;
}

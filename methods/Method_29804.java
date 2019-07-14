public boolean isEffectiveBroadcastId(long broadcastId){
  ensureBroadcastAndIdFromArguments();
  return hasEffectiveBroadcast() && getEffectiveBroadcastId() == broadcastId;
}

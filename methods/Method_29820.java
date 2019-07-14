protected BroadcastResource setArguments(long broadcastId,Broadcast broadcast){
  FragmentUtils.getArgumentsBuilder(this).putLong(EXTRA_BROADCAST_ID,broadcastId).putParcelable(EXTRA_BROADCAST,broadcast);
  return this;
}

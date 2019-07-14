private static BroadcastResource newInstance(long broadcastId,Broadcast broadcast){
  return new BroadcastResource().setArguments(broadcastId,broadcast);
}

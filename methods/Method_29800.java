private static BroadcastAndCommentListResource newInstance(long broadcastId,Broadcast broadcast){
  return new BroadcastAndCommentListResource().setArguments(broadcastId,broadcast);
}

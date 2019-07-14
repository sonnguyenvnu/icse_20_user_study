private boolean canSendComment(){
  Broadcast broadcast=mResource.getEffectiveBroadcast();
  return broadcast != null && broadcast.canComment();
}

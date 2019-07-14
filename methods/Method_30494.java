public ApiRequest<Broadcast> likeBroadcast(long broadcastId,boolean like){
  if (like) {
    return mFrodoService.likeBroadcast(broadcastId);
  }
 else {
    return mFrodoService.unlikeBroadcast(broadcastId);
  }
}

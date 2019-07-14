public static void onCommentRemoved(Broadcast broadcast,Object eventSource){
  if (broadcast == null) {
    return;
  }
  --broadcast.commentCount;
  EventBusUtils.postAsync(new BroadcastUpdatedEvent(broadcast,eventSource));
}

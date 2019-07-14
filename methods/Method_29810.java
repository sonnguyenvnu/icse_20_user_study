public static void onCommentListChanged(Broadcast broadcast,List<Comment> commentList,Object eventSource){
  if (broadcast == null || commentList == null) {
    return;
  }
  if (broadcast.commentCount < commentList.size()) {
    broadcast.commentCount=commentList.size();
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(broadcast,eventSource));
  }
}

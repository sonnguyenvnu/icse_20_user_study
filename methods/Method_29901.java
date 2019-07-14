@Override public void onCommentBroadcast(Broadcast broadcast,View sharedView){
  openBroadcast(broadcast,sharedView,broadcast.canComment() && broadcast.commentCount == 0);
}

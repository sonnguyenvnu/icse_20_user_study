public void write(long broadcastId,long commentId,Context context){
  add(new DeleteBroadcastCommentWriter(broadcastId,commentId,this),context);
}

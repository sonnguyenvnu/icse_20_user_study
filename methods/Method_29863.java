public void write(long broadcastId,String comment,Context context){
  add(new SendBroadcastCommentWriter(broadcastId,comment,this),context);
}

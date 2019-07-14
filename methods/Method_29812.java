protected BroadcastCommentListResource setArguments(long broadcastId){
  FragmentUtils.getArgumentsBuilder(this).putLong(EXTRA_BROADCAST_ID,broadcastId);
  return this;
}

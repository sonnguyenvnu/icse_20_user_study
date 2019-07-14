@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastCommentSent(BroadcastCommentSentEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.broadcastId == mBroadcastId) {
    appendAndNotifyListener(Collections.singletonList(event.comment));
  }
}

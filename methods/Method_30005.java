@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastCommentSendError(BroadcastCommentSendErrorEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.broadcastId == mBroadcastId) {
    updateSendCommentStatus();
  }
}

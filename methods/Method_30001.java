@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastSendError(BroadcastSendErrorEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.writerId == mWriterId) {
    mWriterId=0;
    updateSendStatus();
  }
}

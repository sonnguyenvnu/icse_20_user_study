@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastSent(BroadcastSentEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.writerId == mWriterId) {
    mSent=true;
    mWriterId=0;
    finish();
  }
}

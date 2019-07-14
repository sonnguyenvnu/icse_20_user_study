@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastRebroadcasted(BroadcastRebroadcastedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (mBroadcastResource.isEffectiveBroadcastId(event.broadcastId)) {
    mRebroadcasted=true;
    finish();
  }
}

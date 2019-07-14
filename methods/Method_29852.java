@Override public void onStart(){
  super.onStart();
  EventBusUtils.postAsync(new BroadcastWriteStartedEvent(mBroadcastId,this));
}

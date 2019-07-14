@Override public void onStart(){
  if (mHasImages) {
    sendWithImages();
  }
 else {
    sendSimple();
  }
  EventBusUtils.postAsync(new BroadcastWriteStartedEvent(mId,this));
}

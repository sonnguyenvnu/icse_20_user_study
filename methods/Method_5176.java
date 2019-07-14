@Override public final synchronized void prepareSourceInternal(@Nullable TransferListener mediaTransferListener){
  super.prepareSourceInternal(mediaTransferListener);
  playbackThreadHandler=new Handler(this::handleMessage);
  if (mediaSourcesPublic.isEmpty()) {
    notifyListener();
  }
 else {
    shuffleOrder=shuffleOrder.cloneAndInsert(0,mediaSourcesPublic.size());
    addMediaSourcesInternal(0,mediaSourcesPublic);
    scheduleListenerNotification();
  }
}

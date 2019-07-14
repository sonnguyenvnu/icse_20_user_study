private void notifyListener(){
  listenerNotificationScheduled=false;
  EventDispatcher<Runnable> actionsOnCompletion=pendingOnCompletionActions;
  pendingOnCompletionActions=new EventDispatcher<>();
  refreshSourceInfo(new ConcatenatedTimeline(mediaSourceHolders,windowCount,periodCount,shuffleOrder,isAtomic),null);
  Assertions.checkNotNull(playbackThreadHandler).obtainMessage(MSG_ON_COMPLETION,actionsOnCompletion).sendToTarget();
}

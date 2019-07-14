private void scheduleListenerNotification(@Nullable Handler handler,@Nullable Runnable actionOnCompletion){
  if (!listenerNotificationScheduled) {
    Assertions.checkNotNull(playbackThreadHandler).obtainMessage(MSG_NOTIFY_LISTENER).sendToTarget();
    listenerNotificationScheduled=true;
  }
  if (actionOnCompletion != null && handler != null) {
    pendingOnCompletionActions.addListener(handler,actionOnCompletion);
  }
}

private void notifyListeners(Runnable listenerNotificationRunnable){
  boolean isRunningRecursiveListenerNotification=!pendingListenerNotifications.isEmpty();
  pendingListenerNotifications.addLast(listenerNotificationRunnable);
  if (isRunningRecursiveListenerNotification) {
    return;
  }
  while (!pendingListenerNotifications.isEmpty()) {
    pendingListenerNotifications.peekFirst().run();
    pendingListenerNotifications.removeFirst();
  }
}

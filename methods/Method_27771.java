@Override public void onNotifyNotificationChanged(@NonNull GroupedNotificationModel notification){
  if (adapter != null) {
    adapter.removeItem(notification);
  }
}

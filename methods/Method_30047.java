@Override public void onNotificationCountChanged(int requestCode,NotificationCount newNotificationCount){
  MainActivity activity=(MainActivity)getActivity();
  if (activity != null) {
    activity.onDoumailUnreadCountUpdate(getUnreadCount());
  }
}

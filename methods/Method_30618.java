private void onNotificationListUpdated(){
  MainActivity activity=(MainActivity)getActivity();
  if (activity != null) {
    activity.onNotificationUnreadCountUpdate(getUnreadCount());
  }
}

@Override public void onNotificationRemoved(int requestCode,int position){
  mNotificationAdapter.remove(position);
  onNotificationListUpdated();
}

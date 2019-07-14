@Override public void onUpdateReadState(GroupedNotificationModel item,int position){
  if (onNotificationChangedListener != null)   onNotificationChangedListener.onNotificationChanged(item,0);
  adapter.swapItem(item,position);
}

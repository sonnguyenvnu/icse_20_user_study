@Override public void onRemove(int position){
  hideProgress();
  GroupedNotificationModel model=adapter.getItem(position);
  if (model != null) {
    if (onNotificationChangedListener != null)     onNotificationChangedListener.onNotificationChanged(model,1);
  }
  adapter.removeItem(position);
  invalidateMenu();
}

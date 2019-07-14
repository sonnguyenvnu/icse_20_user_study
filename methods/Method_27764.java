@Override public void onMarkAllAsRead(@NonNull List<GroupedNotificationModel> data){
  manageDisposable(RxHelper.getObservable(Observable.fromIterable(data)).filter(group -> group.getType() == GroupedNotificationModel.ROW).filter(group -> group.getNotification() != null && group.getNotification().isUnread()).map(GroupedNotificationModel::getNotification).subscribe(notification -> {
    notification.setUnread(false);
    manageDisposable(notification.save(notification));
    sendToView(view -> view.onReadNotification(notification));
  }
,this::onError));
}

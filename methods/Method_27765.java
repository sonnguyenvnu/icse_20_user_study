@Override public void onMarkReadByRepo(@NonNull List<GroupedNotificationModel> data,@NonNull Repo repo){
  manageDisposable(RxHelper.getObservable(Observable.fromIterable(data)).filter(group -> group.getType() == GroupedNotificationModel.ROW).filter(group -> group.getNotification() != null && group.getNotification().isUnread()).filter(group -> group.getNotification().getRepository().getFullName().equalsIgnoreCase(repo.getFullName())).map(GroupedNotificationModel::getNotification).subscribe(notification -> {
    notification.setUnread(false);
    manageDisposable(notification.save(notification));
    sendToView(view -> view.onReadNotification(notification));
  }
,this::onError));
}

@Override public void onWorkOffline(){
  if (notifications.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Notification.getUnreadNotifications().toObservable()).flatMap(notifications -> Observable.just(GroupedNotificationModel.onlyNotifications(notifications))).subscribe(models -> sendToView(view -> view.onNotifyAdapter(models))));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}

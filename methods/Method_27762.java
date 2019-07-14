@Override public void onWorkOffline(){
  if (notifications.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Notification.getAllNotifications().toObservable()).flatMap(notifications -> Observable.just(GroupedNotificationModel.construct(notifications))).subscribe(models -> sendToView(view -> view.onNotifyAdapter(models))));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}

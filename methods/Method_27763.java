@Override public void onCallApi(){
  Observable<List<GroupedNotificationModel>> observable=RestProvider.getNotificationService(PrefGetter.isEnterprise()).getAllNotifications().flatMap(response -> {
    manageDisposable(Notification.save(response.getItems()));
    if (response.getItems() != null && !response.getItems().isEmpty()) {
      return Observable.just(GroupedNotificationModel.construct(response.getItems()));
    }
    return Observable.empty();
  }
);
  makeRestCall(observable.doOnComplete(() -> sendToView(BaseMvp.FAView::hideProgress)),response -> sendToView(view -> view.onNotifyAdapter(response)));
}

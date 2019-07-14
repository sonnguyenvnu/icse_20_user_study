@Override public void onCallApi(){
  Observable<List<GroupedNotificationModel>> observable=RestProvider.getNotificationService(PrefGetter.isEnterprise()).getNotifications(ParseDateFormat.getLastWeekDate()).flatMap(response -> {
    if (response.getItems() != null)     manageDisposable(Notification.save(response.getItems()));
    return Observable.just(GroupedNotificationModel.onlyNotifications(response.getItems()));
  }
);
  makeRestCall(observable,response -> sendToView(view -> view.onNotifyAdapter(response)));
}

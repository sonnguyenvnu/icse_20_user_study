@Override public void onWorkOffline(){
  if (eventsModels.isEmpty() && InputHelper.isEmpty(user)) {
    manageDisposable(RxHelper.getObservable(Event.getEvents(Login.getUser().getLogin()).toObservable()).subscribe(modelList -> {
      if (modelList != null) {
        sendToView(view -> view.onNotifyAdapter(modelList,1));
      }
    }
,Throwable::printStackTrace));
  }
 else {
    sendToView(FeedsMvp.View::hideProgress);
  }
}

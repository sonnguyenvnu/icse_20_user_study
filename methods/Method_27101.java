@NonNull public static Disposable updateEntry(@NonNull long pullRequestId){
  return RxHelper.getObservable(Observable.fromPublisher(e -> {
    PinnedPullRequests pinned=get(pullRequestId);
    if (pinned != null) {
      pinned.setEntryCount(pinned.getEntryCount() + 1);
      App.getInstance().getDataStore().toBlocking().update(pinned);
      e.onNext("");
    }
    e.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}

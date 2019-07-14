@NonNull public static Disposable updateEntry(@NonNull String repoFullName){
  return RxHelper.getObservable(Observable.fromPublisher(e -> {
    PinnedRepos pinned=get(repoFullName);
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

@NonNull public static Disposable updateEntry(long issueId){
  return RxHelper.getObservable(Observable.fromPublisher(e -> {
    PinnedIssues pinned=get(issueId);
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

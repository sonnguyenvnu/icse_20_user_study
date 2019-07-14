public static void migrateToVersion4(){
  RxHelper.getObservable(Observable.fromPublisher(e -> {
    try {
      Login login=Login.getUser();
      if (login == null) {
        e.onComplete();
        return;
      }
      BlockingEntityStore<Persistable> reactiveEntityStore=App.getInstance().getDataStore().toBlocking();
      List<PinnedRepos> pinnedRepos=reactiveEntityStore.select(PinnedRepos.class).where(LOGIN.isNull()).get().toList();
      if (pinnedRepos != null) {
        for (        PinnedRepos pinnedRepo : pinnedRepos) {
          pinnedRepo.setRepoFullName(login.getLogin());
          reactiveEntityStore.update(pinnedRepo);
        }
      }
      Logger.e("Hello");
    }
 catch (    Exception ignored) {
      e.onError(ignored);
    }
    e.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}

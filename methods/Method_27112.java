public static Disposable save(@NonNull List<Release> models,@NonNull String repoId,@NonNull String login){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
      dataSource.delete(Release.class).where(Release.REPO_ID.equal(repoId).and(Release.LOGIN.equal(login))).get().value();
      if (!models.isEmpty()) {
        for (        Release releasesModel : models) {
          dataSource.delete(Release.class).where(Release.ID.eq(releasesModel.getId())).get().value();
          releasesModel.setRepoId(repoId);
          releasesModel.setLogin(login);
          dataSource.insert(releasesModel);
        }
      }
      s.onNext("");
    }
 catch (    Exception e) {
      s.onError(e);
    }
    s.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}

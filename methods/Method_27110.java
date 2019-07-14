public static Disposable save(@NonNull List<PullRequest> models,@NonNull String repoId,@NonNull String login){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
      dataSource.delete(PullRequest.class).where(REPO_ID.equal(repoId).and(LOGIN.equal(login))).get().value();
      if (!models.isEmpty()) {
        for (        PullRequest pullRequest : models) {
          dataSource.delete(PullRequest.class).where(PullRequest.ID.eq(pullRequest.getId())).get().value();
          pullRequest.setRepoId(repoId);
          pullRequest.setLogin(login);
          dataSource.insert(pullRequest);
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

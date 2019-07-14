public static Disposable save(@NonNull List<IssueEvent> models,@NonNull String repoId,@NonNull String login,@NonNull String issueId){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
      dataSource.delete(IssueEvent.class).where(LOGIN.equal(login).and(REPO_ID.equal(repoId)).and(ISSUE_ID.equal(issueId))).get().value();
      if (!models.isEmpty()) {
        for (        IssueEvent issueEventModel : models) {
          dataSource.delete(IssueEvent.class).where(IssueEvent.ID.eq(issueEventModel.getId())).get().value();
          issueEventModel.setIssueId(issueId);
          issueEventModel.setLogin(login);
          issueEventModel.setRepoId(repoId);
          dataSource.insert(issueEventModel);
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

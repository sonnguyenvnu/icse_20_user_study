public Single<PullRequest> save(PullRequest entity){
  return RxHelper.getSingle(App.getInstance().getDataStore().delete(PullRequest.class).where(PullRequest.ID.eq(entity.getId())).get().single().flatMap(observer -> App.getInstance().getDataStore().insert(entity)));
}

public Single<RepoFile> save(RepoFile entity){
  return RxHelper.getSingle(App.getInstance().getDataStore().insert(entity));
}

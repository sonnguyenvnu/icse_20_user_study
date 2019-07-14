public Single<Commit> save(Commit entity){
  return RxHelper.getSingle(App.getInstance().getDataStore().upsert(entity));
}

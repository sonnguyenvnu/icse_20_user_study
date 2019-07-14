public static Observable<RepoFile> save(@NonNull List<RepoFile> models,@NonNull String login,@NonNull String repoId){
  ReactiveEntityStore<Persistable> singleEntityStore=App.getInstance().getDataStore();
  return RxHelper.safeObservable(singleEntityStore.delete(RepoFile.class).where(REPO_ID.eq(repoId).and(LOGIN.eq(login))).get().single().toObservable().flatMap(integer -> Observable.fromIterable(models)).flatMap(filesModel -> {
    filesModel.setRepoId(repoId);
    filesModel.setLogin(login);
    return filesModel.save(filesModel).toObservable();
  }
));
}

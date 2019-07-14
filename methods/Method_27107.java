@NonNull public static Observable<List<PinnedRepos>> getMenuRepos(){
  return App.getInstance().getDataStore().select(PinnedRepos.class).where(LOGIN.eq(Login.getUser().getLogin())).orderBy(ENTRY_COUNT.desc(),ID.desc()).limit(5).get().observable().toList().toObservable();
}

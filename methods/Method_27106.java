@NonNull public static Single<List<PinnedRepos>> getMyPinnedRepos(){
  return App.getInstance().getDataStore().select(PinnedRepos.class).where(LOGIN.eq(Login.getUser().getLogin()).or(LOGIN.isNull())).orderBy(ENTRY_COUNT.desc(),ID.desc()).get().observable().toList();
}

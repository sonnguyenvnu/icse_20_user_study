@NonNull public static Single<List<Gist>> getMyPinnedGists(){
  return App.getInstance().getDataStore().select(PinnedGists.class).where(LOGIN.eq(Login.getUser().getLogin()).or(LOGIN.isNull())).orderBy(ENTRY_COUNT.desc(),ID.desc()).get().observable().map(PinnedGists::getGist).toList();
}

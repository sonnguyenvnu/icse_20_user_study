@NonNull public static Single<List<Issue>> getMyPinnedIssues(){
  return App.getInstance().getDataStore().select(PinnedIssues.class).where(LOGIN.eq(Login.getUser().getLogin()).or(LOGIN.isNull())).orderBy(ENTRY_COUNT.desc(),ID.desc()).get().observable().map(PinnedIssues::getIssue).toList();
}

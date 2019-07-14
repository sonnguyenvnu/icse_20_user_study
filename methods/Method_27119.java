public static Single<List<RepoFile>> getFiles(@NonNull String login,@NonNull String repoId){
  return App.getInstance().getDataStore().select(RepoFile.class).where(REPO_ID.eq(repoId).and(LOGIN.eq(login))).orderBy(TYPE.asc()).get().observable().toList();
}

public static Observable<RepoFile> getFile(@NonNull String login,@NonNull String repoId,@NonNull String sha){
  return App.getInstance().getDataStore().select(RepoFile.class).where(REPO_ID.eq(repoId).and(LOGIN.eq(login)).and(SHA.eq(sha))).orderBy(TYPE.asc()).get().observable();
}

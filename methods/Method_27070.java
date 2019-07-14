public static Single<List<Commit>> getCommits(@NonNull String repoId,@NonNull String login,long pullRequestNumber){
  return App.getInstance().getDataStore().select(Commit.class).where(REPO_ID.eq(repoId).and(LOGIN.eq(login)).and(PULL_REQUEST_NUMBER.eq(pullRequestNumber))).get().observable().toList();
}

public static Single<List<Repo>> getMyRepos(@NonNull String reposOwner){
  return App.getInstance().getDataStore().select(Repo.class).where(REPOS_OWNER.eq(reposOwner)).orderBy(UPDATED_AT.desc()).get().observable().toList();
}

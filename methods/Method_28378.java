private void updatePinned(Repo repoModel){
  PinnedRepos pinnedRepos=PinnedRepos.get(repoModel.getFullName());
  if (pinnedRepos != null) {
    pinnedRepos.setPinnedRepo(repoModel);
    manageObservable(PinnedRepos.update(pinnedRepos).toObservable());
  }
}

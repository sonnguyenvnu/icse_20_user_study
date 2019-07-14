public static boolean pinUpin(@NonNull Repo repo){
  PinnedRepos pinnedRepos=get(repo.getFullName());
  if (pinnedRepos == null) {
    PinnedRepos pinned=new PinnedRepos();
    pinned.setRepoFullName(repo.getFullName());
    pinned.setLogin(Login.getUser().getLogin());
    pinned.setPinnedRepo(repo);
    try {
      App.getInstance().getDataStore().toBlocking().insert(pinned);
      return true;
    }
 catch (    Exception ignored) {
    }
    return false;
  }
 else {
    delete(pinnedRepos.getId());
    return false;
  }
}

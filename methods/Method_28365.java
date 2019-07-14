@Override public void onUpdatePinnedEntry(@NonNull String repoId,@NonNull String login){
  manageDisposable(PinnedRepos.updateEntry(login + "/" + repoId));
}

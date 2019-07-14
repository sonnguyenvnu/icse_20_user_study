@Override public void onWatch(){
  if (getRepo() == null)   return;
  isWatched=!isWatched;
  sendToView(view -> {
    view.onRepoWatched(isWatched);
    view.onChangeWatchedCount(isWatched);
  }
);
}

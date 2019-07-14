@Override public void onWorkOffline(@NonNull String sha,@NonNull String repoId,@NonNull String login){
  manageDisposable(RxHelper.getObservable(Commit.getCommit(sha,repoId,login)).subscribe(commit -> {
    commitModel=commit;
    sendToView(CommitPagerMvp.View::onSetup);
  }
));
}

@Override public void onActivityCreate(@NonNull String repoId,@NonNull String login,int navTyp){
  this.login=login;
  this.repoId=repoId;
  this.navTyp=navTyp;
  if (getRepo() == null || !isApiCalled()) {
    callApi(navTyp);
  }
 else {
    sendToView(RepoPagerMvp.View::onInitRepo);
  }
}

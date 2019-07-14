@Override public void onCheckStarring(){
  if (getRepo() != null) {
    String login=login();
    String name=repoId();
    manageDisposable(RxHelper.getObservable(RestProvider.getRepoService(isEnterprise()).checkStarring(login,name)).doOnSubscribe(disposable -> sendToView(view -> view.onEnableDisableStar(false))).doOnNext(response -> sendToView(view -> view.onRepoStarred(isStarred=response.code() == 204))).subscribe(booleanResponse -> {
    }
,throwable -> {
      isStarred=false;
      sendToView(view -> view.onRepoStarred(isStarred));
    }
));
  }
}

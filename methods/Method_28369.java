@Override public void onCheckWatching(){
  if (getRepo() != null) {
    String login=login();
    String name=repoId();
    manageDisposable(RxHelper.getObservable(RestProvider.getRepoService(isEnterprise()).isWatchingRepo(login,name)).doOnSubscribe(disposable -> sendToView(view -> view.onEnableDisableWatch(false))).doOnNext(subscriptionModel -> sendToView(view -> view.onRepoWatched(isWatched=subscriptionModel.isSubscribed()))).subscribe(o -> {
    }
,throwable -> {
      isWatched=false;
      sendToView(view -> view.onRepoWatched(isWatched));
    }
));
  }
}

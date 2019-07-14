@Override public void onWorkOffline(){
  if (!InputHelper.isEmpty(login()) && !InputHelper.isEmpty(repoId())) {
    manageDisposable(RxHelper.getObservable(Repo.getRepo(repoId,login).toObservable()).subscribe(repoModel -> {
      repo=repoModel;
      if (repo != null) {
        sendToView(view -> {
          view.onInitRepo();
          view.onNavigationChanged(RepoPagerMvp.CODE);
        }
);
      }
 else {
        callApi(navTyp);
      }
    }
,Throwable::printStackTrace));
  }
 else {
    sendToView(RepoPagerMvp.View::onFinishActivity);
  }
}

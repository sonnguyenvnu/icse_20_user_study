private void callApi(int navTyp){
  if (InputHelper.isEmpty(login) || InputHelper.isEmpty(repoId))   return;
  makeRestCall(Observable.zip(RestProvider.getRepoService(isEnterprise()).getRepo(login(),repoId()),RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,Login.getUser().getLogin()),(repo1,booleanResponse) -> {
    isCollaborator=booleanResponse.code() == 204;
    return repo1;
  }
),repoModel -> {
    this.repo=repoModel;
    manageDisposable(this.repo.save(repo));
    updatePinned(repoModel);
    sendToView(view -> {
      view.onInitRepo();
      view.onNavigationChanged(navTyp);
    }
);
    onCheckStarring();
    onCheckWatching();
  }
);
}

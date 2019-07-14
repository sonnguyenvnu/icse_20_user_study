@Override public void checkAuthority(@NonNull String login,@NonNull String repoId){
  manageViewDisposable(RxHelper.getObservable(RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,Login.getUser().getLogin())).subscribe(booleanResponse -> {
    isCollaborator=booleanResponse.code() == 204;
    sendToView(CreateIssueMvp.View::onShowIssueMisc);
  }
,Throwable::printStackTrace));
}

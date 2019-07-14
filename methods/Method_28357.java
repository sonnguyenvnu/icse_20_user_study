@SuppressWarnings("ConstantConditions") @Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (isOk && bundle != null) {
    boolean isDelete=bundle.getBoolean(BundleConstant.EXTRA_TWO);
    boolean fork=bundle.getBoolean(BundleConstant.EXTRA);
    if (fork) {
      if (getPresenter().login() != null && getPresenter().repoId() != null && !getPresenter().isForked()) {
        GithubActionService.startForRepo(this,getPresenter().login(),getPresenter().repoId(),GithubActionService.FORK_REPO,isEnterprise());
        getPresenter().onFork();
      }
    }
    if (isDelete)     getPresenter().onDeleteRepo();
  }
}

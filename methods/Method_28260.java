@Override public void onActivityCreated(@Nullable Intent intent){
  if (intent != null && intent.getExtras() != null) {
    issueNumber=intent.getExtras().getInt(BundleConstant.ID);
    login=intent.getExtras().getString(BundleConstant.EXTRA);
    repoId=intent.getExtras().getString(BundleConstant.EXTRA_TWO);
    showToRepoBtn=intent.getExtras().getBoolean(BundleConstant.EXTRA_THREE);
    if (pullRequest != null) {
      sendToView(view -> view.onSetupIssue(false));
      return;
    }
 else     if (issueNumber > 0 && !InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
      callApi();
      return;
    }
  }
  sendToView(view -> view.onSetupIssue(false));
}

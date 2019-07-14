@Override public void onActivityCreated(@Nullable Intent intent){
  Logger.e(isEnterprise());
  if (intent != null && intent.getExtras() != null) {
    issueModel=intent.getExtras().getParcelable(BundleConstant.ITEM);
    issueNumber=intent.getExtras().getInt(BundleConstant.ID);
    login=intent.getExtras().getString(BundleConstant.EXTRA);
    repoId=intent.getExtras().getString(BundleConstant.EXTRA_TWO);
    showToRepoBtn=intent.getExtras().getBoolean(BundleConstant.EXTRA_THREE);
    commentId=intent.getExtras().getLong(BundleConstant.EXTRA_SIX);
    if (issueModel != null) {
      issueNumber=issueModel.getNumber();
      sendToView(view -> view.onSetupIssue(false));
      return;
    }
 else     if (issueNumber > 0 && !InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
      getIssueFromApi();
      return;
    }
  }
  sendToView(view -> view.onSetupIssue(false));
}

@Override public void onActivityCreated(@Nullable Intent intent){
  if (intent != null && intent.getExtras() != null) {
    sha=intent.getExtras().getString(BundleConstant.ID);
    login=intent.getExtras().getString(BundleConstant.EXTRA);
    repoId=intent.getExtras().getString(BundleConstant.EXTRA_TWO);
    showToRepoBtn=intent.getExtras().getBoolean(BundleConstant.EXTRA_THREE);
    if (commitModel != null) {
      sendToView(CommitPagerMvp.View::onSetup);
      return;
    }
 else     if (!InputHelper.isEmpty(sha) && !InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
      makeRestCall(RestProvider.getRepoService(isEnterprise()).getCommit(login,repoId,sha).flatMap(commit -> {
        if (commit.getGitCommit() != null && commit.getGitCommit().getMessage() != null) {
          MarkdownModel markdownModel=new MarkdownModel();
          markdownModel.setContext(login + "/" + repoId);
          markdownModel.setText(commit.getGitCommit().getMessage());
          return RestProvider.getRepoService(isEnterprise()).convertReadmeToHtml(markdownModel);
        }
        return Observable.just(commit);
      }
,(commit,u) -> {
        if (!InputHelper.isEmpty(u) && u instanceof String) {
          commit.getGitCommit().setMessage(u.toString());
        }
        return commit;
      }
),commit -> {
        commitModel=commit;
        commitModel.setRepoId(repoId);
        commitModel.setLogin(login);
        sendToView(CommitPagerMvp.View::onSetup);
        manageObservable(commitModel.save(commitModel).toObservable());
      }
);
      return;
    }
  }
  sendToView(CommitPagerMvp.View::onSetup);
}

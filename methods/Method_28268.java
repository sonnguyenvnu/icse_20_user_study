@Override public void onOpenCloseIssue(){
  if (getPullRequest() != null) {
    IssueRequestModel requestModel=IssueRequestModel.clone(getPullRequest(),true);
    manageDisposable(RxHelper.getObservable(RestProvider.getPullRequestService(isEnterprise()).editPullRequest(login,repoId,issueNumber,requestModel)).doOnSubscribe(disposable -> sendToView(view -> view.showProgress(0))).subscribe(issue -> {
      if (issue != null) {
        sendToView(view -> view.showSuccessIssueActionMsg(getPullRequest().getState() == IssueState.open));
        issue.setRepoId(getPullRequest().getRepoId());
        issue.setLogin(getPullRequest().getLogin());
        pullRequest=issue;
        sendToView(view -> view.onSetupIssue(false));
      }
    }
,throwable -> sendToView(view -> view.showErrorIssueActionMsg(getPullRequest().getState() == IssueState.open))));
  }
}

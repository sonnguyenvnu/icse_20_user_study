@Override public void onWorkOffline(){
  if (pullRequest == null) {
    manageDisposable(PullRequest.getPullRequestByNumber(issueNumber,repoId,login).subscribe(pullRequestModel -> {
      if (pullRequestModel != null) {
        pullRequest=pullRequestModel;
        sendToView(view -> view.onSetupIssue(false));
      }
    }
));
  }
}

@Override public void onUpdatePullRequest(@NonNull PullRequest pullRequestModel){
  this.pullRequest.setTitle(pullRequestModel.getTitle());
  this.pullRequest.setBody(pullRequestModel.getBody());
  this.pullRequest.setBodyHtml(pullRequestModel.getBodyHtml());
  this.pullRequest.setLogin(login);
  this.pullRequest.setRepoId(repoId);
  manageObservable(pullRequest.save(pullRequest).toObservable());
  sendToView(view -> view.onSetupIssue(true));
}

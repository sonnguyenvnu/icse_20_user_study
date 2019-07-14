private void callApi(){
  Login loggedInUser=Login.getUser();
  if (loggedInUser == null)   return;
  makeRestCall(RxHelper.getObservable(Observable.zip(RestProvider.getPullRequestService(isEnterprise()).getPullRequest(login,repoId,issueNumber),RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,loggedInUser.getLogin()),RestProvider.getIssueService(isEnterprise()).getIssue(login,repoId,issueNumber),(pullRequestModel,booleanResponse,issue) -> {
    this.pullRequest=pullRequestModel;
    if (issue != null) {
      this.pullRequest.setReactions(issue.getReactions());
      this.pullRequest.setTitle(issue.getTitle());
      this.pullRequest.setBody(issue.getBody());
      this.pullRequest.setBodyHtml(issue.getBodyHtml());
    }
    this.pullRequest.setLogin(login);
    this.pullRequest.setRepoId(repoId);
    isCollaborator=booleanResponse.code() == 204;
    return pullRequest;
  }
)),pullRequest -> {
    sendToView(view -> view.onSetupIssue(false));
    manageDisposable(PinnedPullRequests.updateEntry(pullRequest.getId()));
    manageObservable(pullRequest.save(pullRequest).toObservable());
  }
);
}

@Override public void onUpdateIssue(@NonNull Issue issue){
  this.issueModel.setBody(issue.getBody());
  this.issueModel.setBodyHtml(issue.getBodyHtml());
  this.issueModel.setTitle(issue.getTitle());
  this.issueModel.setLogin(login);
  this.issueModel.setRepoId(repoId);
  manageObservable(issueModel.save(issueModel).toObservable());
  sendToView(view -> view.onSetupIssue(true));
}

private void setupIssue(Issue issue){
  issueModel=issue;
  issueModel.setRepoId(repoId);
  issueModel.setLogin(login);
  sendToView(view -> view.onSetupIssue(false));
  manageDisposable(PinnedIssues.updateEntry(issue.getId()));
}

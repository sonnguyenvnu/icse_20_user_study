private void getIssueFromApi(){
  Login loginUser=Login.getUser();
  if (loginUser == null)   return;
  makeRestCall(RxHelper.getObservable(Observable.zip(RestProvider.getIssueService(isEnterprise()).getIssue(login,repoId,issueNumber),RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,loginUser.getLogin()),(issue,booleanResponse) -> {
    isCollaborator=booleanResponse.code() == 204;
    return issue;
  }
)),this::setupIssue);
}

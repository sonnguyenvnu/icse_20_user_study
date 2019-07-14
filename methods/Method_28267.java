@Override public void onLockUnlockConversations(String reason){
  PullRequest currentPullRequest=getPullRequest();
  if (currentPullRequest == null)   return;
  IssueService service=RestProvider.getIssueService(isEnterprise());
  LockIssuePrModel model=null;
  if (!isLocked() && !InputHelper.isEmpty(reason)) {
    model=new LockIssuePrModel(true,reason);
  }
  Observable<Response<Boolean>> observable=RxHelper.getObservable(model == null ? service.unlockIssue(login,repoId,issueNumber) : service.lockIssue(model,login,repoId,issueNumber));
  makeRestCall(observable,booleanResponse -> {
    int code=booleanResponse.code();
    if (code == 204) {
      pullRequest.setLocked(!isLocked());
      sendToView(view -> view.onSetupIssue(false));
    }
  }
);
}

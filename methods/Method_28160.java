@Override public void onLockUnlockIssue(String reason){
  Issue currentIssue=getIssue();
  if (currentIssue == null)   return;
  String login=getLogin();
  String repoId=getRepoId();
  int number=currentIssue.getNumber();
  LockIssuePrModel model=null;
  if (!isLocked() && !InputHelper.isEmpty(reason)) {
    model=new LockIssuePrModel(true,reason);
  }
  IssueService issueService=RestProvider.getIssueService(isEnterprise());
  Observable<Response<Boolean>> observable=RxHelper.getObservable(model == null ? issueService.unlockIssue(login,repoId,number) : issueService.lockIssue(model,login,repoId,number));
  makeRestCall(observable,booleanResponse -> {
    int code=booleanResponse.code();
    if (code == 204) {
      issueModel.setLocked(!isLocked());
      sendToView(view -> view.onSetupIssue(true));
    }
    sendToView(IssuePagerMvp.View::hideProgress);
  }
);
}

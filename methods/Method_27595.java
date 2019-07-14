@Override public void onSearch(@NonNull String query,boolean isOpen,boolean isIssue,boolean isEnterprise){
  getPresenter().setEnterprise(isEnterprise);
  this.query=query;
  this.issueState=isOpen ? IssueState.open : IssueState.closed;
  this.isIssue=isIssue;
  onClear();
  onRefresh();
}

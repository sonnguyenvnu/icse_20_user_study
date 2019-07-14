@Override public void onFilterIssue(@NonNull IssueState issueState){
  if (this.issueState != null && this.issueState != issueState) {
    this.issueState=issueState;
    getArguments().putSerializable(BundleConstant.ITEM,issueState);
    getLoadMore().reset();
    adapter.clear();
    onRefresh();
  }
}

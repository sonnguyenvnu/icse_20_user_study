@OnCheckedChanged(R.id.sortByUpdated) void onSortIssues(boolean isChecked){
  RepoIssuesPagerFragment pagerView=(RepoIssuesPagerFragment)AppHelper.getFragmentByTag(getSupportFragmentManager(),RepoIssuesPagerFragment.TAG);
  if (pagerView != null) {
    pagerView.onChangeIssueSort(isChecked);
  }
  hideFilterLayout();
}

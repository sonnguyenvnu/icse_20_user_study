@Override public void onAddSelected(){
  RepoIssuesPagerFragment pagerView=(RepoIssuesPagerFragment)AppHelper.getFragmentByTag(getSupportFragmentManager(),RepoIssuesPagerFragment.TAG);
  if (pagerView != null) {
    pagerView.onAddIssue();
  }
}

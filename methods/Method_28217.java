@Override public void onChangeIssueSort(boolean isLastUpdated){
  if (pager == null || pager.getAdapter() == null)   return;
  RepoClosedIssuesFragment closedIssues=(RepoClosedIssuesFragment)pager.getAdapter().instantiateItem(pager,1);
  if (closedIssues != null)   closedIssues.onRefresh(isLastUpdated);
  RepoOpenedIssuesFragment openedIssues=(RepoOpenedIssuesFragment)pager.getAdapter().instantiateItem(pager,0);
  if (openedIssues != null)   openedIssues.onRefresh(isLastUpdated);
}

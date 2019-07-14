@Override public void setCurrentItem(int index,boolean refresh){
  if (pager == null || pager.getAdapter() == null)   return;
  if (!refresh)   pager.setCurrentItem(index,true);
  if (index == 1 && refresh) {
    RepoClosedIssuesFragment closedIssues=(RepoClosedIssuesFragment)pager.getAdapter().instantiateItem(pager,1);
    if (closedIssues != null)     closedIssues.onRefresh();
  }
 else   if (index == 0 && refresh) {
    RepoOpenedIssuesFragment openedIssues=(RepoOpenedIssuesFragment)pager.getAdapter().instantiateItem(pager,0);
    if (openedIssues != null)     openedIssues.onRefresh();
  }
}

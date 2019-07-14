@Override public void onAddIssue(){
  if (pager.getCurrentItem() != 0)   pager.setCurrentItem(0);
  RepoOpenedIssuesFragment repoOpenedIssuesView=(RepoOpenedIssuesFragment)pager.getAdapter().instantiateItem(pager,0);
  repoOpenedIssuesView.onAddIssue();
}

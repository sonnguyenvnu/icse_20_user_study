@Override public void onSearchSelected(){
  boolean isOpen=true;
  RepoIssuesPagerFragment pagerView=(RepoIssuesPagerFragment)AppHelper.getFragmentByTag(getSupportFragmentManager(),RepoIssuesPagerFragment.TAG);
  if (pagerView != null) {
    isOpen=pagerView.getCurrentItem() == 0;
  }
  FilterIssuesActivity.startActivity(this,getPresenter().login(),getPresenter().repoId(),true,isOpen,isEnterprise());
}

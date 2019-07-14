@Override public void onScrollTop(int index){
  super.onScrollTop(index);
  if (pager != null && pager.getAdapter() != null) {
    MyPullRequestFragment myIssuesFragment=(MyPullRequestFragment)pager.getAdapter().instantiateItem(pager,pager.getCurrentItem());
    if (myIssuesFragment != null) {
      myIssuesFragment.onScrollTop(0);
    }
  }
}

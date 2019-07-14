protected void hideAndClearReviews(){
  getPresenter().getCommitComment().clear();
  AnimHelper.mimicFabVisibility(false,prReviewHolder,null);
  if (pager == null || pager.getAdapter() == null)   return;
  PullRequestFilesFragment fragment=(PullRequestFilesFragment)pager.getAdapter().instantiateItem(pager,2);
  if (fragment != null) {
    fragment.onRefresh();
  }
}

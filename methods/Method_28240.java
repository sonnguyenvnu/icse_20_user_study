@Override public void onSetupIssue(boolean update){
  hideProgress();
  if (getPresenter().getPullRequest() == null) {
    return;
  }
  invalidateOptionsMenu();
  PullRequest pullRequest=getPresenter().getPullRequest();
  setTaskName(pullRequest.getRepoId() + " - " + pullRequest.getTitle());
  updateViews(pullRequest);
  if (update) {
    PullRequestTimelineFragment issueDetailsView=getPullRequestTimelineFragment();
    if (issueDetailsView != null && getPresenter().getPullRequest() != null) {
      issueDetailsView.onUpdateHeader();
    }
  }
 else {
    if (pager.getAdapter() == null) {
      pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForPullRequest(this,pullRequest)));
      tabs.setupWithViewPager(pager);
      tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager){
        @Override public void onTabReselected(        TabLayout.Tab tab){
          super.onTabReselected(tab);
          onScrollTop(tab.getPosition());
        }
      }
);
    }
 else {
      onUpdateTimeline();
    }
  }
  pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageSelected(    int position){
      hideShowFab();
      super.onPageSelected(position);
    }
  }
);
  initTabs(pullRequest);
  hideShowFab();
  AnimHelper.mimicFabVisibility(getPresenter().hasReviewComments(),prReviewHolder,null);
  reviewsCount.setText(String.format("%s",getPresenter().getCommitComment().size()));
}

@Override public void onSetupIssue(boolean isUpdate){
  hideProgress();
  if (getPresenter().getIssue() == null) {
    return;
  }
  onUpdateMenu();
  Issue issueModel=getPresenter().getIssue();
  setTaskName(issueModel.getRepoId() + " - " + issueModel.getTitle());
  setTitle(String.format("#%s",issueModel.getNumber()));
  if (getSupportActionBar() != null) {
    getSupportActionBar().setSubtitle(issueModel.getRepoId());
  }
  updateViews(issueModel);
  if (isUpdate) {
    IssueTimelineFragment issueDetailsView=getIssueTimelineFragment();
    if (issueDetailsView != null && getPresenter().getIssue() != null) {
      issueDetailsView.onUpdateHeader();
    }
  }
 else {
    if (pager.getAdapter() == null) {
      Logger.e(getPresenter().commentId);
      pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForIssues(this,getPresenter().commentId)));
    }
 else {
      onUpdateTimeline();
    }
  }
  if (!getPresenter().isLocked() || getPresenter().isOwner()) {
    pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
      @Override public void onPageSelected(      int position){
        super.onPageSelected(position);
        hideShowFab();
      }
    }
);
  }
  hideShowFab();
}

@OnClick(R.id.fab) void onFabClicked(){
  if (navType == RepoPagerMvp.ISSUES) {
    fab.hide(new FloatingActionButton.OnVisibilityChangedListener(){
      @Override public void onHidden(      FloatingActionButton fab){
        super.onHidden(fab);
        if (appbar != null)         appbar.setExpanded(false,true);
        bottomNavigation.setExpanded(false,true);
        AnimHelper.mimicFabVisibility(true,filterLayout,null);
      }
    }
);
  }
 else   if (navType == RepoPagerMvp.PULL_REQUEST) {
    RepoPullRequestPagerFragment pullRequestPagerView=(RepoPullRequestPagerFragment)AppHelper.getFragmentByTag(getSupportFragmentManager(),RepoPullRequestPagerFragment.TAG);
    if (pullRequestPagerView != null) {
      FilterIssuesActivity.startActivity(this,getPresenter().login(),getPresenter().repoId(),false,pullRequestPagerView.getCurrentItem() == 0,isEnterprise());
    }
  }
 else {
    fab.hide();
  }
}

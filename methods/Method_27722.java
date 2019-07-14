private void onInit(@Nullable Bundle savedInstanceState){
  if (isLoggedIn()) {
    if (savedInstanceState == null) {
      boolean attachFeeds=true;
      if (getIntent().getAction() != null) {
        if (getIntent().getAction().equalsIgnoreCase("myPulls")) {
          navType=MainMvp.PULL_REQUESTS;
          getSupportFragmentManager().beginTransaction().replace(R.id.container,MyPullsPagerFragment.newInstance(),MyPullsPagerFragment.TAG).commit();
          bottomNavigation.setSelectedIndex(2,true);
          attachFeeds=false;
        }
 else         if (getIntent().getAction().equalsIgnoreCase("myIssues")) {
          navType=MainMvp.ISSUES;
          getSupportFragmentManager().beginTransaction().replace(R.id.container,MyIssuesPagerFragment.newInstance(),MyIssuesPagerFragment.TAG).commit();
          bottomNavigation.setSelectedIndex(1,true);
          attachFeeds=false;
        }
      }
      hideShowShadow(navType == MainMvp.FEEDS);
      if (attachFeeds) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,FeedsFragment.newInstance(null),FeedsFragment.TAG).commit();
      }
    }
    Typeface myTypeface=TypeFaceHelper.getTypeface();
    bottomNavigation.setDefaultTypeface(myTypeface);
    bottomNavigation.setOnMenuItemClickListener(getPresenter());
  }
}

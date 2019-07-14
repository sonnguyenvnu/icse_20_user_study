@SuppressWarnings("ConstantConditions") @Override public void onModuleChanged(@NonNull FragmentManager fragmentManager,@MainMvp.NavigationType int type){
  Fragment currentVisible=getVisibleFragment(fragmentManager);
  FeedsFragment homeView=(FeedsFragment)getFragmentByTag(fragmentManager,FeedsFragment.TAG);
  MyPullsPagerFragment pullRequestView=(MyPullsPagerFragment)getFragmentByTag(fragmentManager,MyPullsPagerFragment.TAG);
  MyIssuesPagerFragment issuesView=(MyIssuesPagerFragment)getFragmentByTag(fragmentManager,MyIssuesPagerFragment.TAG);
switch (type) {
case MainMvp.PROFILE:
    sendToView(MainMvp.View::onOpenProfile);
  break;
case MainMvp.FEEDS:
if (homeView == null) {
  onAddAndHide(fragmentManager,FeedsFragment.newInstance(null),currentVisible);
}
 else {
  onShowHideFragment(fragmentManager,homeView,currentVisible);
}
break;
case MainMvp.PULL_REQUESTS:
if (pullRequestView == null) {
onAddAndHide(fragmentManager,MyPullsPagerFragment.newInstance(),currentVisible);
}
 else {
onShowHideFragment(fragmentManager,pullRequestView,currentVisible);
}
break;
case MainMvp.ISSUES:
if (issuesView == null) {
onAddAndHide(fragmentManager,MyIssuesPagerFragment.newInstance(),currentVisible);
}
 else {
onShowHideFragment(fragmentManager,issuesView,currentVisible);
}
break;
}
}

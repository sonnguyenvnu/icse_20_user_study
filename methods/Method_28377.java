@Override public void onMenuItemSelect(@IdRes int id,int position,boolean fromUser){
  if (id == R.id.issues && (getRepo() != null && !getRepo().isHasIssues())) {
    sendToView(RepoPagerMvp.View::disableIssueTab);
    return;
  }
  if (getView() != null && isViewAttached() && fromUser) {
    getView().onNavigationChanged(position);
  }
}

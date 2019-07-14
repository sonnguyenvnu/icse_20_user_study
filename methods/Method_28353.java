@Override public void disableIssueTab(){
  showMessage(R.string.error,R.string.repo_issues_is_disabled);
  bottomNavigation.setMenuItemEnabled(1,false);
  bottomNavigation.setSelectedIndex(this.navType,true);
}

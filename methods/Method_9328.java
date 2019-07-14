private void needFinishActivity(){
  clearCurrentState();
  if (getParentActivity() instanceof LaunchActivity) {
    if (newAccount) {
      newAccount=false;
      ((LaunchActivity)getParentActivity()).switchToAccount(currentAccount,false);
      finishFragment();
    }
 else {
      presentFragment(new DialogsActivity(null),true);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged);
    }
  }
 else   if (getParentActivity() instanceof ExternalActionActivity) {
    ((ExternalActionActivity)getParentActivity()).onFinishLogin();
  }
}

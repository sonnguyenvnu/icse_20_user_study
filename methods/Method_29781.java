public static boolean ensureActiveAccountAvailability(Activity activity){
  boolean accountAvailable=true;
  if (!hasAccount()) {
    accountAvailable=false;
    addAccount(activity,activity.getIntent());
  }
 else   if (!hasActiveAccount()) {
    accountAvailable=false;
    selectAccount(activity,activity.getIntent());
  }
  if (!accountAvailable) {
    activity.finish();
  }
  return accountAvailable;
}

public static void selectAccount(Activity activity,Intent onSelectedIntent){
  if (getAccounts().length == 0) {
    throw new IllegalStateException("Should have checked for hasAccount()");
  }
  activity.startActivity(SelectAccountActivity.makeIntent(onSelectedIntent,activity));
}

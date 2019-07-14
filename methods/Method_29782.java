public static String getUserName(Account account){
  return AccountPreferences.forAccount(account).getString(AccountContract.KEY_USER_NAME,null);
}

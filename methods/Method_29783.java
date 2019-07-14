public static void setUserId(Account account,long userId){
  AccountPreferences.forAccount(account).putLong(AccountContract.KEY_USER_ID,userId);
}

public static Account getActiveAccount(){
  Account account=getAccountByName(getActiveAccountName());
  if (account != null) {
    return account;
  }
 else {
    removeActiveAccountName();
    return null;
  }
}

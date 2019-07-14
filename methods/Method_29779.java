public static Account getRecentTwoAccount(){
  Account activeAccount=getActiveAccount();
  if (activeAccount == null) {
    return null;
  }
  Account recentOneAccount=getRecentOneAccount();
  if (recentOneAccount == null) {
    return null;
  }
  String accountName=getRecentTwoAccountName();
  if (!TextUtils.isEmpty(accountName) && !TextUtils.equals(accountName,activeAccount.name) && !TextUtils.equals(accountName,recentOneAccount.name)) {
    Account account=getAccountByName(accountName);
    if (account != null) {
      return account;
    }
  }
  for (  Account account : getAccounts()) {
    if (!account.equals(activeAccount) && !account.equals(recentOneAccount)) {
      setRecentTwoAccountName(account.name);
      return account;
    }
  }
  removeRecentTwoAccountName();
  return null;
}

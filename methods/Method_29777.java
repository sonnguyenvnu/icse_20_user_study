public static void setActiveAccount(Account account){
  if (account == null) {
    removeActiveAccountName();
    return;
  }
  Account oldActiveAccount=getActiveAccount();
  setActiveAccountName(account.name);
  if (oldActiveAccount != null) {
    if (TextUtils.equals(getRecentOneAccountName(),account.name)) {
      setRecentOneAccountName(oldActiveAccount.name);
    }
 else     if (TextUtils.equals(getRecentTwoAccountName(),account.name)) {
      setRecentTwoAccountName(oldActiveAccount.name);
    }
 else {
      setRecentTwoAccountName(getRecentOneAccountName());
      setRecentOneAccountName(oldActiveAccount.name);
    }
  }
  ApiAuthenticators.getInstance().notifyActiveAccountChanged();
}

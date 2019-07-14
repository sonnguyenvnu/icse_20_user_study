public static AccountPreferences forAccount(Account account){
  if (account == null) {
    throw new IllegalArgumentException("account is null");
  }
synchronized (INSTANCES_LOCK) {
    AccountPreferences accountPreferences=INSTANCES.get(account);
    if (accountPreferences == null) {
      accountPreferences=new AccountPreferences(account);
      INSTANCES.put(account,accountPreferences);
    }
    return accountPreferences;
  }
}

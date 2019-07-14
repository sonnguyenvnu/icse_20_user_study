public static AccountInfo adaptToAccountInfo(GlobitexAccounts globitexAccounts){
  return new AccountInfo(globitexAccounts.getAccounts().get(0).getAccount(),adaptToWallet(globitexAccounts));
}

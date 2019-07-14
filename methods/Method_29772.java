public static void addOnAccountListUpdatedListener(OnAccountsUpdateListener listener){
  getAccountManager().addOnAccountsUpdatedListener(listener,null,false);
}

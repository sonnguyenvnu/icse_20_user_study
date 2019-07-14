private Intent makeSuccessIntent(String accountName){
  Intent intent=new Intent();
  intent.putExtra(AccountManager.KEY_ACCOUNT_NAME,accountName);
  intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE,AccountContract.ACCOUNT_TYPE);
  return intent;
}

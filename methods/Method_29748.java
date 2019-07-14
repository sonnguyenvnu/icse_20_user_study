private Bundle makeSuccessBundle(String accountName){
  Bundle bundle=new Bundle();
  bundle.putString(AccountManager.KEY_ACCOUNT_NAME,accountName);
  bundle.putString(AccountManager.KEY_ACCOUNT_TYPE,AccountContract.ACCOUNT_TYPE);
  return bundle;
}

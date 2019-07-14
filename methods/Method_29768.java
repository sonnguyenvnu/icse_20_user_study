public static AccountManagerFuture<Bundle> updatePassword(Activity activity,Account account,AccountManagerCallback<Bundle> callback,Handler handler){
  return getAccountManager().updateCredentials(account,AccountContract.AUTH_TOKEN_TYPE_FRODO,null,activity,callback,handler);
}

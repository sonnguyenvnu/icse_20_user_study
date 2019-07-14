public static AccountManagerFuture<Bundle> confirmPassword(Activity activity,Account account,AccountManagerCallback<Bundle> callback,Handler handler){
  return getAccountManager().confirmCredentials(account,null,activity,callback,handler);
}

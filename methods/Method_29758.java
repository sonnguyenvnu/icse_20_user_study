public static Intent makeIntent(AccountAuthenticatorResponse response,@AuthMode String authMode,Context context){
  return new Intent(context,AuthenticatorActivity.class).putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,response).putExtra(EXTRA_AUTH_MODE,authMode);
}

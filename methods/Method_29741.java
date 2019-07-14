@Override public Bundle confirmCredentials(AccountAuthenticatorResponse response,Account account,Bundle options) throws NetworkErrorException {
  return makeIntentBundle(AuthenticatorActivity.makeIntent(response,AuthenticatorActivity.AUTH_MODE_CONFIRM,account.name,mContext));
}

@Override public Bundle updateCredentials(AccountAuthenticatorResponse response,Account account,String authTokenType,Bundle options) throws NetworkErrorException {
  return makeIntentBundle(makeUpdateCredentialIntent(response,account));
}

@ReactMethod public void configure(final ReadableMap config,final Promise promise){
  final ReadableArray scopes=config.hasKey("scopes") ? config.getArray("scopes") : Arguments.createArray();
  final String webClientId=config.hasKey("webClientId") ? config.getString("webClientId") : null;
  final boolean offlineAccess=config.hasKey("offlineAccess") && config.getBoolean("offlineAccess");
  final boolean forceConsentPrompt=config.hasKey("forceConsentPrompt") && config.getBoolean("forceConsentPrompt");
  final String accountName=config.hasKey("accountName") ? config.getString("accountName") : null;
  final String hostedDomain=config.hasKey("hostedDomain") ? config.getString("hostedDomain") : null;
  GoogleSignInOptions options=getSignInOptions(createScopesArray(scopes),webClientId,offlineAccess,forceConsentPrompt,accountName,hostedDomain);
  _apiClient=GoogleSignIn.getClient(getReactApplicationContext(),options);
  promise.resolve(null);
}

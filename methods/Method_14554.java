public static OAuthConsumer getConsumer(Provider provider){
  if (provider == null) {
    throw new RuntimeException("Provider can't be null");
  }
  String[] consumer_info=infos.get(provider.getHost());
  if (consumer_info == null) {
    throw new RuntimeException("Can't find secrets for provider '" + provider.getHost() + "'");
  }
  OAuthConsumer oauthConsumer=provider.createConsumer(consumer_info[0],consumer_info[1]);
  oauthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy(provider.getRealm()));
  return oauthConsumer;
}

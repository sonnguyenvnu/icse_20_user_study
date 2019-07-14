public Collection<OAuth2AccessToken> findTokensByClientId(String clientId){
  Collection<OAuth2AccessToken> result=clientIdToAccessTokenStore.get(clientId);
  return result != null ? Collections.<OAuth2AccessToken>unmodifiableCollection(result) : Collections.<OAuth2AccessToken>emptySet();
}

public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,String userName){
  Collection<OAuth2AccessToken> result=userNameToAccessTokenStore.get(getApprovalKey(clientId,userName));
  return result != null ? Collections.<OAuth2AccessToken>unmodifiableCollection(result) : Collections.<OAuth2AccessToken>emptySet();
}

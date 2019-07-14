@Transactional public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
  OAuth2AccessToken existingAccessToken=tokenStore.getAccessToken(authentication);
  OAuth2RefreshToken refreshToken=null;
  if (existingAccessToken != null) {
    if (existingAccessToken.isExpired()) {
      if (existingAccessToken.getRefreshToken() != null) {
        refreshToken=existingAccessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
      }
      tokenStore.removeAccessToken(existingAccessToken);
    }
 else {
      tokenStore.storeAccessToken(existingAccessToken,authentication);
      return existingAccessToken;
    }
  }
  if (refreshToken == null) {
    refreshToken=createRefreshToken(authentication);
  }
 else   if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
    ExpiringOAuth2RefreshToken expiring=(ExpiringOAuth2RefreshToken)refreshToken;
    if (System.currentTimeMillis() > expiring.getExpiration().getTime()) {
      refreshToken=createRefreshToken(authentication);
    }
  }
  OAuth2AccessToken accessToken=createAccessToken(authentication,refreshToken);
  tokenStore.storeAccessToken(accessToken,authentication);
  refreshToken=accessToken.getRefreshToken();
  if (refreshToken != null) {
    tokenStore.storeRefreshToken(refreshToken,authentication);
  }
  return accessToken;
}

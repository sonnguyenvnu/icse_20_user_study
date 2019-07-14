@Override public OAuth2Request request(String uriOrUrl){
  if (accessTokenInfo == null) {
    authorize();
  }
  if (accessTokenInfo.isExpire()) {
    refreshToken();
  }
  OAuth2Request request=createRequest(getRealUrl(uriOrUrl));
  request.onTokenExpired(retry -> {
    refreshToken();
    applyTokenParam(request);
    retry.doReTry();
  }
);
  request.onRefreshTokenExpired(reTry -> {
    setAccessTokenInfo(requestAccessToken());
    applyTokenParam(request);
    reTry.doReTry();
  }
);
  applyTokenParam(request);
  return request;
}

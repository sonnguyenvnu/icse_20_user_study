@Override public void invalidateOAuth2Token() throws TwitterException {
  if (token == null) {
    throw new IllegalStateException("OAuth 2 Bearer Token is not available.");
  }
  HttpParameter[] params=new HttpParameter[1];
  params[0]=new HttpParameter("access_token",token.getAccessToken());
  OAuth2Token _token=token;
  boolean succeed=false;
  try {
    token=null;
    HttpResponse res=http.post(conf.getOAuth2InvalidateTokenURL(),params,this,null);
    if (res.getStatusCode() != 200) {
      throw new TwitterException("Invalidating OAuth 2 Bearer Token failed.",res);
    }
    succeed=true;
  }
  finally {
    if (!succeed) {
      token=_token;
    }
  }
}

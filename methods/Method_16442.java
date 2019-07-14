protected AccessTokenModel entityToModel(OAuth2AccessToken token){
  AccessTokenModel model=new AccessTokenModel();
  model.setAccess_token(token.getAccessToken());
  model.setRefresh_token(token.getRefreshToken());
  model.setExpires_in(token.getExpiresIn());
  if (token.getScope() != null) {
    model.setScope(token.getScope().stream().reduce((t1,t2) -> t1.concat(",").concat(t2)).orElse(""));
  }
 else {
    model.setScope("public");
  }
  model.setToken_type("bearer");
  return model;
}

@Override protected void applyBasicAuthParam(OAuth2Request request){
  request.param(OAuth2Constants.grant_type,GrantType.password);
  request.param("username",username);
  request.param("password",serverConfig.getClientSecret());
  request.header(OAuth2Constants.authorization,encodeAuthorization(username.concat(":").concat(password)));
}

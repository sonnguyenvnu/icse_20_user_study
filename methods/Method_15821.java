@Override protected void applyBasicAuthParam(OAuth2Request request){
  super.applyBasicAuthParam(request);
  request.param(OAuth2Constants.grant_type,GrantType.authorization_code);
}

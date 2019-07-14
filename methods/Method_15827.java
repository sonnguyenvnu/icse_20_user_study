protected void applyTokenParam(OAuth2Request request){
  request.param(access_token,getAccessToken().getAccessToken());
  String tokenType=getAccessToken().getTokenType();
  request.header(authorization,"Bearer " + getAccessToken().getAccessToken());
}

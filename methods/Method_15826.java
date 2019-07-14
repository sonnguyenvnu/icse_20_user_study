protected void applyBasicAuthParam(OAuth2Request request){
  request.param(client_id,serverConfig.getClientId());
  request.param(client_secret,serverConfig.getClientSecret());
  request.param(redirect_uri,serverConfig.getRedirectUri());
  request.header(authorization,encodeAuthorization(serverConfig.getClientId().concat(":").concat(serverConfig.getClientSecret())));
}

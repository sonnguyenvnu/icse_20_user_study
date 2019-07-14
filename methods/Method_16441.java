@PostMapping @ApiOperation(value="??token",notes="?????????: http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html") @ApiImplicitParams({@ApiImplicitParam(paramType="query",name=OAuth2Constants.client_id,required=true),@ApiImplicitParam(paramType="query",name=OAuth2Constants.client_secret),@ApiImplicitParam(paramType="query",name=OAuth2Constants.refresh_token),@ApiImplicitParam(paramType="query",name=OAuth2Constants.redirect_uri),@ApiImplicitParam(paramType="query",name=OAuth2Constants.code),@ApiImplicitParam(paramType="query",name=OAuth2Constants.scope,example="user-info:get,share:add"),@ApiImplicitParam(paramType="header",name=OAuth2Constants.authorization,example="Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW")}) public AccessTokenModel requestToken(@RequestParam("grant_type") @ApiParam(allowableValues=GrantType.authorization_code + "," + GrantType.client_credentials + "," + GrantType.password + "," + GrantType.refresh_token + "," + GrantType.implicit) String grant_type,HttpServletRequest request){
  OAuth2AccessToken accessToken=null;
switch (grant_type) {
case GrantType.authorization_code:
    accessToken=oAuth2Granter.grant(GrantType.authorization_code,new HttpAuthorizationCodeTokenRequest(request));
  break;
case GrantType.client_credentials:
accessToken=oAuth2Granter.grant(GrantType.client_credentials,new HttpClientCredentialRequest(request));
break;
case GrantType.implicit:
accessToken=oAuth2Granter.grant(GrantType.implicit,new HttpImplicitRequest(request));
break;
case GrantType.password:
accessToken=oAuth2Granter.grant(GrantType.password,new HttpPasswordRequest(request));
break;
case GrantType.refresh_token:
accessToken=oAuth2Granter.grant(GrantType.refresh_token,new HttpRefreshTokenRequest(request));
break;
default :
ErrorType.UNSUPPORTED_GRANT_TYPE.throwThis(GrantTokenException::new);
}
publisher.publishEvent(new OAuth2GrantEvent(accessToken));
return entityToModel(accessToken);
}

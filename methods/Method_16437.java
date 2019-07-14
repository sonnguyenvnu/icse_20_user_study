@GetMapping(params="response_type=token") @ApiOperation(value="implicit????token",tags="OAuth2.0-??-??token") @ApiImplicitParam(paramType="query",name=OAuth2Constants.client_id,required=true) public ImplicitAccessTokenModel authorizeByImplicit(@RequestParam(value="redirect_uri") String redirect_uri,@RequestParam(value="state") String state,HttpServletRequest request){
  ImplicitRequest implicitRequest=new HttpImplicitRequest(request);
  OAuth2AccessToken accessToken=oAuth2Granter.grant(GrantType.implicit,implicitRequest);
  publisher.publishEvent(new OAuth2GrantEvent(accessToken));
  ImplicitAccessTokenModel model=new ImplicitAccessTokenModel();
  model.setState(state);
  model.setToken_type("example");
  model.setAccess_token(accessToken.getAccessToken());
  model.setExpires_in(accessToken.getExpiresIn());
  model.setRedirect_uri(redirect_uri);
  return model;
}

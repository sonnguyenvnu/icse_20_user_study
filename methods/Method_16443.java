@GetMapping @ApiOperation("??accessToken????????") public ResponseMessage<Authentication> getLoginUser(@RequestParam("access_token") String access_token){
  OAuth2AccessToken auth2AccessEntity=accessTokenService.getTokenByAccessToken(access_token);
  if (null == auth2AccessEntity) {
    throw new GrantTokenException(ErrorType.EXPIRED_TOKEN);
  }
  return ResponseMessage.ok(AuthenticationHolder.get(auth2AccessEntity.getOwnerId()));
}

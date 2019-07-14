@GetMapping("/{userId}") @ApiOperation("??accessToken?????????") public ResponseMessage<Authentication> getUserById(@PathVariable("userId") String userId,@RequestParam("access_token") String access_token){
  OAuth2AccessToken auth2AccessEntity=accessTokenService.getTokenByAccessToken(access_token);
  if (null == auth2AccessEntity) {
    throw new GrantTokenException(ErrorType.EXPIRED_TOKEN);
  }
  if (auth2AccessEntity.getScope() == null || (!auth2AccessEntity.getScope().contains("*") && !auth2AccessEntity.getScope().contains("user:get"))) {
    throw new GrantTokenException(ErrorType.UNAUTHORIZED_CLIENT);
  }
  Authentication info=AuthenticationHolder.get(userId);
  if (info == null) {
    throw new NotFoundException("user:" + userId + " not found");
  }
  return ResponseMessage.ok(info);
}

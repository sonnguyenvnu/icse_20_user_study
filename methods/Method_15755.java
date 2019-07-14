@PostMapping("/user-token/{token}/{type}/{userId}/{maxInactiveInterval}") @ApiOperation("????????") @Authorize(action=Permission.ACTION_ADD) public ResponseMessage<UserToken> signIn(@PathVariable String token,@PathVariable String type,@PathVariable String userId,@PathVariable long maxInactiveInterval){
  return ok(userTokenManager.signIn(token,type,userId,maxInactiveInterval));
}

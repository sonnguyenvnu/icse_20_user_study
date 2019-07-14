@GetMapping("/user-token/reset") @Authorize(merge=false) @ApiOperation("?????????") public ResponseMessage<Boolean> resetToken(){
  UserToken token=UserTokenHolder.currentToken();
  if (token != null) {
    userTokenManager.signOutByToken(token.getToken());
  }
  return ok(true);
}

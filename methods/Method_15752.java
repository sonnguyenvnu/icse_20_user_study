@GetMapping("/user-token/user/total") @ApiOperation("?????????????") @Authorize public ResponseMessage<Long> totalUser(){
  return ok(userTokenManager.totalUser());
}

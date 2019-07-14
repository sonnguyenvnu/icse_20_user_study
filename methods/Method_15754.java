@PutMapping("/user-token/user/{userId}/{state}") @ApiOperation("????id????????") @Authorize(action=Permission.ACTION_UPDATE) public ResponseMessage<Void> changeUserState(@PathVariable String userId,@PathVariable TokenState state){
  userTokenManager.changeUserState(userId,state);
  return ok();
}

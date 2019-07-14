@GetMapping("/user-token") @ApiOperation("??????????") @Authorize(action=Permission.ACTION_GET) public ResponseMessage<List<UserToken>> allLoggedUser(){
  return ok(userTokenManager.allLoggedUser());
}

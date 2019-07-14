@GetMapping("/user-token/{token}/touch") @ApiOperation("??token???") @Authorize(action=Permission.ACTION_UPDATE) public ResponseMessage<Void> touch(@PathVariable String token){
  userTokenManager.touch(token);
  return ok();
}

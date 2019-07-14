@GetMapping("/user-auth/{userId}") @ApiOperation("????id?????????") @Authorize(action=Permission.ACTION_GET) public ResponseMessage<Authentication> userAuthInfo(@PathVariable String userId){
  return ok(authenticationManager.getByUserId(userId));
}

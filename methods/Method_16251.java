@Authorize(action=Permission.ACTION_GET) @GetMapping(path="/{id:.+}/authentication") @ApiOperation("?????????") public ResponseMessage<Authentication> getUserAuthentication(@PathVariable String id){
  return ok(authenticationManager.getByUserId(id));
}

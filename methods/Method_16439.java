@GetMapping("/owner/{userId}") @Authorize(action=Permission.ACTION_GET) @ApiOperation("???????????") public ResponseMessage<OAuth2Client> getByOwnerId(@PathVariable String userId){
  return ResponseMessage.ok(repository.getClientByOwnerId(userId));
}

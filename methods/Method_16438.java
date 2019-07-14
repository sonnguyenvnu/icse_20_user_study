@GetMapping("/{id}") @Authorize(action=Permission.ACTION_GET) @ApiOperation("??id?????") public ResponseMessage<OAuth2Client> getById(@PathVariable String id){
  return ResponseMessage.ok(repository.getClientById(id));
}

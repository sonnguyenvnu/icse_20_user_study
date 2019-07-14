@PatchMapping @Authorize(action=Permission.ACTION_UPDATE) @ApiOperation(value="?????",notes="?????????????") public ResponseMessage<OAuth2Client> saveOrUpdate(@RequestBody OAuth2ClientEntity clientEntity){
  Authentication authentication=Authentication.current().orElse(null);
  if (null != authentication) {
    clientEntity.setCreatorId(authentication.getUser().getId());
  }
  clientEntity.setCreateTimeNow();
  return ResponseMessage.ok(repository.save(clientEntity));
}

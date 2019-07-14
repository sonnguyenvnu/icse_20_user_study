@GetMapping("/metas") @Authorize(action=Permission.ACTION_QUERY,description="?????") @ApiOperation("????????") public ResponseMessage<Map<ObjectMetadata.ObjectType,List<? extends ObjectMetadata>>> parseAllObject() throws Exception {
  return parseAllObject(null);
}

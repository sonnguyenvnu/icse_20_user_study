@GetMapping("/metas/{datasourceId}") @Authorize(action=Permission.ACTION_QUERY,description="?????") @ApiOperation("???????????") public ResponseMessage<Map<ObjectMetadata.ObjectType,List<? extends ObjectMetadata>>> parseAllObject(@PathVariable @ApiParam("???ID") String datasourceId) throws Exception {
  DataSourceHolder.switcher().use(datasourceId);
  return ResponseMessage.ok(databaseManagerService.getMetas());
}

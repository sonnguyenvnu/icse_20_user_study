@GetMapping("/transactional/new/{dataSourceId}") @Authorize(action="execute",description="??SQL") @ApiOperation("?????????") public ResponseMessage<String> newTransaction(@PathVariable String dataSourceId) throws Exception {
  DataSourceHolder.switcher().use(dataSourceId);
  return ResponseMessage.ok(databaseManagerService.newTransaction(dataSourceId));
}

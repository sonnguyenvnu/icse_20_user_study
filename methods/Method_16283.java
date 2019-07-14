@PostMapping(value="/transactional/execute/{transactionalId}/{dataSourceId}",consumes=MediaType.TEXT_PLAIN_VALUE) @Authorize(action="execute",description="??SQL") @ApiOperation(value="????????????SQL") public ResponseMessage<List<SqlExecuteResult>> executeTransactional(@PathVariable @ApiParam("??ID") String transactionalId,@PathVariable @ApiParam("???ID") String dataSourceId,@ApiParam("SQL??") @RequestBody String sqlLines) throws Exception {
  DataSourceHolder.switcher().use(dataSourceId);
  return ResponseMessage.ok(databaseManagerService.execute(transactionalId,SqlExecuteRequest.builder().sql(parseSql(sqlLines,dataSourceId)).build()));
}

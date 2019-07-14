@PostMapping(value="/execute/{datasourceId}",consumes=MediaType.TEXT_PLAIN_VALUE) @Authorize(action="execute",description="??SQL") @ApiOperation(value="???????SQL") public ResponseMessage<List<SqlExecuteResult>> execute(@PathVariable @ApiParam("???ID") String datasourceId,@RequestBody @ApiParam("SQL??") String sqlLines) throws Exception {
  DataSourceHolder.switcher().use(datasourceId);
  return ResponseMessage.ok(databaseManagerService.execute(SqlExecuteRequest.builder().sql(parseSql(sqlLines,datasourceId)).build()));
}

@PostMapping(value="/transactional/execute/{transactionalId}",consumes=MediaType.TEXT_PLAIN_VALUE) @Authorize(action="execute",description="??SQL") @ApiOperation(value="??????SQL") public ResponseMessage<List<SqlExecuteResult>> executeTransactional(@PathVariable @ApiParam("??ID") String transactionalId,@ApiParam("SQL??") @RequestBody String sqlLines) throws Exception {
  return ResponseMessage.ok(databaseManagerService.execute(transactionalId,SqlExecuteRequest.builder().sql(parseSql(sqlLines,null)).build()));
}

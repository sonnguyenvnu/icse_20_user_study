@GetMapping("/transactional/new") @Authorize(action="execute",description="??SQL") @ApiOperation("????") public ResponseMessage<String> newTransaction() throws Exception {
  return ResponseMessage.ok(databaseManagerService.newTransaction());
}

@PostMapping("/transactional/{id}/rollback") @Authorize(action="execute",description="??SQL") @ApiOperation("????") public ResponseMessage<String> rollbackTransaction(@PathVariable String id) throws Exception {
  databaseManagerService.rollback(id);
  return ResponseMessage.ok();
}

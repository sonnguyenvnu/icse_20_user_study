@GetMapping("/{id}/execute") @ApiOperation("????") @Authorize(action="execute",description="????") public ResponseMessage<Object> executeForGet(@PathVariable String id,@RequestParam(required=false) Map<String,Object> parameters) throws Exception {
  if (parameters == null) {
    parameters=new HashMap<>();
  }
  Object result=scriptExecutorService.execute(id,parameters);
  return ResponseMessage.ok(result);
}

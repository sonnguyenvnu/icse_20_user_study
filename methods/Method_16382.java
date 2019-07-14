@GetMapping("/{id}/{version:\\d+}") @Authorize(action=Permission.ACTION_GET) @ApiOperation("?????????") public ResponseMessage<DynamicFormColumnBindEntity> selectDeployed(@PathVariable String id,@PathVariable int version){
  return ResponseMessage.ok(dynamicFormService.selectDeployed(id,version));
}

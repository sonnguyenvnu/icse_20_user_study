@PatchMapping("/batch") @Authorize(action=Permission.ACTION_ADD) @ApiOperation("???????") public ResponseMessage<List<String>> add(@RequestBody List<DynamicFormColumnEntity> columnEntities){
  return ResponseMessage.ok(dynamicFormService.saveOrUpdateColumn(columnEntities));
}

@GetMapping("/{formId}/single") @ApiOperation("??????????") @Authorize(action=Permission.ACTION_QUERY) public ResponseMessage<Object> selectSingle(@PathVariable String formId,QueryParamEntity paramEntity){
  return ResponseMessage.ok(dynamicFormOperationService.selectSingle(formId,paramEntity));
}

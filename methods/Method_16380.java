@GetMapping("/{formId}") @Authorize(action=Permission.ACTION_GET) @ApiOperation("????????") public ResponseMessage<List<DynamicFormColumnEntity>> getByFormId(@PathVariable String formId){
  return ResponseMessage.ok(dynamicFormService.selectColumnsByFormId(formId));
}

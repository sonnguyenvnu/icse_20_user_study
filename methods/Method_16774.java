@GetMapping("/{type}/form/{processDefineId}") @ApiOperation("??????????????") @Authorize(merge=false) @SuppressWarnings("all") public ResponseMessage<PagerResult<Object>> getFormData(@PathVariable Type type,@PathVariable String processDefineId,QueryParamEntity query,Authentication authentication){
  Query.empty(query).nest().when(type != null,q -> type.applyQueryTerm(q,authentication.getUser().getId())).end();
  return ResponseMessage.ok(workFlowFormService.selectProcessForm(processDefineId,query));
}

/** 
 * ??????
 */
@GetMapping @ApiOperation("????????") @Authorize(action=Permission.ACTION_QUERY) public ResponseMessage<PagerResult<ProcessDefinitionInfo>> queryProcessList(QueryParamEntity param){
  ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery();
  return ResponseMessage.ok(QueryUtils.doQuery(processDefinitionQuery,param,ProcessDefinitionInfo::of));
}

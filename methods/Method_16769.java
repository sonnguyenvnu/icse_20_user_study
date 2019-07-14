/** 
 * ?????????
 */
@GetMapping("/{processInstanceId}/activity") @ApiOperation("?????????????") @Authorize(action=Permission.ACTION_QUERY) public ResponseMessage<ActivityInfo> getProcessInstanceActivity(@PathVariable String processInstanceId){
  HistoricProcessInstance processInstance=bpmTaskService.selectHisProInst(processInstanceId);
  if (processInstance != null) {
    ActivityImpl activity=bpmActivityService.getActivityByProcInstId(processInstance.getProcessDefinitionId(),processInstance.getId());
    return ResponseMessage.ok(ActivityInfo.of(activity));
  }
 else {
    throw new NotFoundException("?????");
  }
}

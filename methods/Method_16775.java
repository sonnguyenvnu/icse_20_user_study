@PostMapping("/next-task-candidate/{taskId}") @Authorize(merge=false) public ResponseMessage<List<CandidateDetail>> candidateList(@PathVariable String taskId,@RequestBody Map<String,Object> data,Authentication authentication){
  Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
  ExecutionEntity execution=(ExecutionEntity)runtimeService.createExecutionQuery().processInstanceId(task.getProcessInstanceId()).activityId(task.getTaskDefinitionKey()).singleResult();
  execution.setTransientVariables(data);
  List<TaskDefinition> taskDefinitions=bpmActivityService.getNextActivities(task.getProcessDefinitionId(),task.getTaskDefinitionKey(),(execution));
  List<CandidateDetail> candidates=taskDefinitions.stream().map(TaskDefinition::getKey).flatMap(key -> processConfigurationService.getActivityConfiguration(authentication.getUser().getId(),task.getProcessDefinitionId(),key).getCandidateInfo(task).stream()).distinct().map(CandidateDetail::of).collect(Collectors.toList());
  return ResponseMessage.ok(candidates);
}

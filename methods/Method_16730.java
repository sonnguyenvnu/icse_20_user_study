@SneakyThrows public void doJumpTask(Task task,String activityId,Consumer<Task> newTaskConsumer){
  ProcessDefinitionEntity entity=(ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
  String sourceId=task.getTaskDefinitionKey();
  ActivityImpl targetActivity=entity.findActivity(activityId);
  ActivityImpl sourceActivity=entity.findActivity(sourceId);
  if (logger.isDebugEnabled()) {
    logger.debug("??[{}({})]??[{}]->[{}]",entity.getName(),entity.getId(),sourceActivity.getId(),targetActivity.getId());
  }
  List<PvmActivity> backActivities=new ArrayList<>();
  findActivity(targetActivity,activity -> activity.getOutgoingTransitions().stream().map(PvmTransition::getDestination).collect(Collectors.toList()),activity -> sourceActivity.getId().equals(activity.getId()),backActivities::add);
  if (!backActivities.isEmpty()) {
    for (    PvmActivity pvmTransition : backActivities) {
      if (logger.isDebugEnabled()) {
        logger.debug("??[{}({})]??[{}]->[{}],?????:{}",entity.getName(),entity.getId(),sourceActivity.getId(),targetActivity.getId(),pvmTransition.getId());
      }
      List<HistoricActivityInstance> instance=historyService.createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).activityId(pvmTransition.getId()).list();
      for (      HistoricActivityInstance historicActivityInstance : instance) {
        sqlExecutor.delete("delete from act_hi_actinst where id_= #{id}",historicActivityInstance);
      }
    }
  }
  TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService;
  taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(task.getExecutionId(),activityId));
  selectNowTask(task.getProcessInstanceId()).forEach(t -> {
    setCandidate(task.getAssignee(),t);
    newTaskConsumer.accept(t);
  }
);
}

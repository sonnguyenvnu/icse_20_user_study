@Override public ActivityImpl getActivityByProcInstId(String procDefId,String procInstId){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).active().singleResult();
  String activityId=processInstance.getActivityId();
  return getProcessDefinition(procDefId).findActivity(activityId);
}

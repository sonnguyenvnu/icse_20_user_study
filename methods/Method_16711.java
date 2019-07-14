@Override public List<ActivityImpl> getUserTasksByProcDefKey(String procDefKey){
  ProcessDefinition definition=repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDefKey).orderByProcessDefinitionVersion().desc().list().get(0);
  String procDefId=definition.getId();
  List<ActivityImpl> activities=findActivities(procDefId,activity -> "userTask".equals(activity.getProperty("type")));
  if (null != activities) {
    activities.sort(Comparator.comparing(ProcessElementImpl::getId));
  }
  return activities;
}

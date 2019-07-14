@Override public Map<String,Object> getUserTasksByProcDefKey(String procDefKey){
  String definitionId=repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDefKey).orderByProcessDefinitionVersion().desc().list().get(0).getId();
  List<ActivityImpl> activities=bpmActivityService.getUserTasksByProcDefId(definitionId);
  Map<String,Object> map=new HashMap<>();
  for (  ActivityImpl activity : activities) {
    map.put(activity.getId(),activity.getProperty("name"));
  }
  return map;
}

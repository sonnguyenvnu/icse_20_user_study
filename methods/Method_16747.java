protected void acceptStartProcessFormData(ProcessInstance instance,Map<String,Object> formData){
  String processDefinitionName;
  try {
    processDefinitionName=((ExecutionEntity)instance).getProcessDefinition().getName();
  }
 catch (  NullPointerException e) {
    processDefinitionName=repositoryService.createProcessDefinitionQuery().processDefinitionId(instance.getProcessDefinitionId()).singleResult().getName();
  }
  formData.put("id",instance.getBusinessKey());
  formData.put("processDefineId",instance.getProcessDefinitionId());
  formData.put("processDefineKey",instance.getProcessDefinitionKey());
  formData.put("processDefineName",processDefinitionName);
  formData.put("processInstanceId",instance.getProcessInstanceId());
}

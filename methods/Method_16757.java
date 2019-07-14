private Map<String,Object> getProcessDefinitionResponse(ProcessDefinitionEntity processDefinition){
  JSONObject pdrJSON=new JSONObject();
  pdrJSON.put("id",processDefinition.getId());
  pdrJSON.put("name",processDefinition.getName());
  pdrJSON.put("key",processDefinition.getKey());
  pdrJSON.put("version",processDefinition.getVersion());
  pdrJSON.put("deploymentId",processDefinition.getDeploymentId());
  pdrJSON.put("isGraphicNotationDefined",isGraphicNotationDefined(processDefinition));
  return pdrJSON;
}

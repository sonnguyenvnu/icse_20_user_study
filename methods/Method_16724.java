@Override public InputStream findProcessPic(String procDefId){
  ProcessDefinition definition=getProcessDefinitionById(procDefId);
  String source=definition.getDiagramResourceName();
  return repositoryService.getResourceAsStream(definition.getDeploymentId(),source);
}

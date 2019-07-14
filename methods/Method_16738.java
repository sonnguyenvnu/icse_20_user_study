@Override public Map<String,Object> getVariablesByProcInstId(String procInstId){
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(procInstId).list();
  String executionId="";
  for (  Execution execution : executions) {
    if (StringUtils.isNullOrEmpty(execution.getParentId())) {
      executionId=execution.getId();
    }
  }
  return runtimeService.getVariables(executionId);
}

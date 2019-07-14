@Override public void closeProcessInstance(String processInstanceId){
  runtimeService.suspendProcessInstanceById(processInstanceId);
}

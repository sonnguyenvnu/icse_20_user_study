@Override public void onEvent(ActivitiEvent event){
  String eventType=event.getType().name().toLowerCase();
  if (event instanceof ActivitiEntityEvent) {
    ActivitiEntityEvent entityEvent=((ActivitiEntityEvent)event);
    Object entity=entityEvent.getEntity();
    if (entity instanceof TaskEntity) {
      TaskEntity task=((TaskEntity)entity);
      ActivityConfiguration activityConfiguration=processConfigurationService.getActivityConfiguration(Authentication.current().map(Authentication::getUser).map(User::getId).orElse(null),event.getProcessDefinitionId(),task.getTaskDefinitionKey());
      TaskEventListener listener=activityConfiguration.getTaskListener(eventType);
      if (null != listener) {
        listener.doEvent(new TaskEvent(){
          @Override public Task getTask(){
            return task;
          }
          @Override public ProcessInstance getProcessInstance(){
            return runtimeService.createProcessInstanceQuery().processInstanceId(event.getProcessInstanceId()).singleResult();
          }
        }
);
      }
    }
  }
  if (eventType.startsWith("process")) {
    ProcessConfiguration configuration=processConfigurationService.getProcessConfiguration(event.getProcessDefinitionId());
    ProcessEventListener listener=configuration.getProcessListener(eventType);
    if (null != listener) {
      listener.doEvent(() -> runtimeService.createProcessInstanceQuery().processInstanceId(event.getProcessInstanceId()).singleResult());
    }
  }
}

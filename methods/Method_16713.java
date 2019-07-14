@Override public List<TaskDefinition> getTaskDefinition(ActivityImpl activityImpl,DelegateExecution execution){
  Set<TaskDefinition> taskDefinitionList=new HashSet<>();
  List<TaskDefinition> nextTaskDefinition;
  if ("userTask".equals(activityImpl.getProperty("type"))) {
    TaskDefinition taskDefinition=((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
    taskDefinitionList.add(taskDefinition);
  }
 else {
    List<PvmTransition> pvmTransitions=activityImpl.getOutgoingTransitions();
    List<PvmTransition> outTransitionsTemp;
    for (    PvmTransition tr : pvmTransitions) {
      PvmActivity pvmActivity=tr.getSource();
      boolean exclusiveGateway="exclusiveGateway".equals(pvmActivity.getProperty("type"));
      boolean parallelGateway="parallelGateway".equals(pvmActivity.getProperty("type"));
      if (exclusiveGateway || parallelGateway) {
        outTransitionsTemp=pvmActivity.getOutgoingTransitions();
        if (outTransitionsTemp.size() == 1) {
          nextTaskDefinition=getTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(),execution);
          taskDefinitionList.addAll(nextTaskDefinition);
        }
 else         if (outTransitionsTemp.size() > 1) {
          for (          PvmTransition transition : outTransitionsTemp) {
            String condition=(String)transition.getProperty(BpmnParse.PROPERTYNAME_CONDITION_TEXT);
            if (StringUtils.isEmpty(condition)) {
              nextTaskDefinition=getTaskDefinition((ActivityImpl)transition.getDestination(),execution);
              if (exclusiveGateway) {
                if (!CollectionUtils.isEmpty(nextTaskDefinition)) {
                  taskDefinitionList.add(nextTaskDefinition.get(0));
                }
              }
 else {
                taskDefinitionList.addAll(nextTaskDefinition);
              }
              continue;
            }
            ExpressionManager expressionManager=((ProcessEngineConfigurationImpl)processEngine.getProcessEngineConfiguration()).getExpressionManager();
            ELContext elContext=expressionManager.getElContext(execution);
            ExpressionFactoryImpl factory=new ExpressionFactoryImpl();
            Object e=factory.createValueExpression(elContext,condition,Object.class).getValue(elContext);
            if (Boolean.TRUE.equals(e)) {
              nextTaskDefinition=getTaskDefinition((ActivityImpl)transition.getDestination(),execution);
              taskDefinitionList.addAll(nextTaskDefinition);
            }
          }
        }
      }
 else       if ("userTask".equals(pvmActivity.getProperty("type"))) {
        taskDefinitionList.add(((UserTaskActivityBehavior)((ActivityImpl)pvmActivity).getActivityBehavior()).getTaskDefinition());
      }
    }
  }
  return new ArrayList<>(taskDefinitionList);
}

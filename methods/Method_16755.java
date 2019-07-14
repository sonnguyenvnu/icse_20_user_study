private List<String> getHighLightedFlows(String processInstanceId,ProcessDefinitionEntity processDefinition){
  List<String> highLightedFlows=new ArrayList<String>();
  List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
  List<String> historicActivityInstanceList=new ArrayList<String>();
  for (  HistoricActivityInstance hai : historicActivityInstances) {
    historicActivityInstanceList.add(hai.getActivityId());
  }
  List<String> highLightedActivities=runtimeService.getActiveActivityIds(processInstanceId);
  historicActivityInstanceList.addAll(highLightedActivities);
  for (  ActivityImpl activity : processDefinition.getActivities()) {
    int index=historicActivityInstanceList.indexOf(activity.getId());
    if (index >= 0 && index + 1 < historicActivityInstanceList.size()) {
      List<PvmTransition> pvmTransitionList=activity.getOutgoingTransitions();
      for (      PvmTransition pvmTransition : pvmTransitionList) {
        String destinationFlowId=pvmTransition.getDestination().getId();
        if (destinationFlowId.equals(historicActivityInstanceList.get(index + 1))) {
          highLightedFlows.add(pvmTransition.getId());
        }
      }
    }
  }
  return highLightedFlows;
}

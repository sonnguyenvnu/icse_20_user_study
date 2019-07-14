@Override public List<TaskDefinition> getNextActivities(String procDefId,String activityId,DelegateExecution execution){
  ActivityImpl activity;
  if (activityId != null) {
    activity=getActivityById(procDefId,activityId);
  }
 else {
    activity=getStartEvent(procDefId);
  }
  List<PvmTransition> pvmTransitions=activity.getOutgoingTransitions();
  return pvmTransitions.stream().map(PvmTransition::getDestination).map(ActivityImpl.class::cast).filter(Objects::nonNull).map(act -> getTaskDefinition(act,execution)).flatMap(Collection::stream).collect(Collectors.toList());
}

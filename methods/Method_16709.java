@Override public List<ActivityImpl> getActivitiesById(String processDefId,String activityId){
  ProcessDefinitionEntity pde=getProcessDefinition(processDefId);
  if (activityId == null) {
    return pde.getActivities();
  }
 else {
    ActivityImpl activity=pde.findActivity(activityId);
    if (null == activity) {
      return new java.util.ArrayList<>();
    }
    return Collections.singletonList(activity);
  }
}

private ActivityImpl findActivity(String procDefId,Predicate<ActivityImpl> predicate){
  ProcessDefinitionEntity pde=getProcessDefinition(procDefId);
  if (pde == null) {
    return null;
  }
  return pde.getActivities().stream().filter(predicate).findFirst().orElse(null);
}

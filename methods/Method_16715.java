private List<ActivityImpl> findActivities(String procDefId,Predicate<ActivityImpl> predicate){
  ProcessDefinitionEntity pde=getProcessDefinition(procDefId);
  if (pde == null) {
    return new ArrayList<>();
  }
  return pde.getActivities().stream().filter(predicate).collect(Collectors.toList());
}

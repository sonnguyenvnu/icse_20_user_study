public Set<String> getOrCreatePausedTriggerGroupsSet(){
  String pausedGrpsSetName=generateName(PAUSED_TRIGGER_GROUPS_SET_PREFIX);
  ToolkitSet<String> temp=toolkit.getSet(pausedGrpsSetName,String.class);
  pausedTriggerGroupsReference.compareAndSet(null,temp);
  return pausedTriggerGroupsReference.get();
}

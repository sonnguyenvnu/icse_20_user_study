public Set<String> getOrCreatePausedGroupsSet(){
  String pausedGrpsSetName=generateName(PAUSED_GROUPS_SET_PREFIX);
  ToolkitSet<String> temp=toolkit.getSet(pausedGrpsSetName,String.class);
  pausedGroupsReference.compareAndSet(null,temp);
  return pausedGroupsReference.get();
}

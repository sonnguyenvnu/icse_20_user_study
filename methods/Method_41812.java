public Set<String> getOrCreateAllGroupsSet(){
  String allGrpSetNames=generateName(ALL_JOBS_GROUP_NAMES_SET_PREFIX);
  ToolkitSet<String> temp=toolkit.getSet(allGrpSetNames,String.class);
  allGroupsReference.compareAndSet(null,temp);
  return allGroupsReference.get();
}

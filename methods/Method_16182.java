protected void initDepCache(){
  depCache=new HashMap<>();
  dependencies.forEach(dependency -> depCache.put(getDepKey(dependency.groupId,dependency.artifactId),dependency));
}

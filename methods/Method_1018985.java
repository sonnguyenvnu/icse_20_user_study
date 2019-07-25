@Override public Set<ClassVertex> roots(){
  return getInput().getClasses().values().stream().map(cn -> new ClassVertex(this,cn)).collect(Collectors.toSet());
}

@Override public SubtypeResolver snapshot(){
  if (_registeredSubtypes == null) {
    return new StdSubtypeResolver();
  }
  return new StdSubtypeResolver(new LinkedHashSet<>(_registeredSubtypes));
}

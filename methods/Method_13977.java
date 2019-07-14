@Override public Set<Value> disallowedValues(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,DISALLOWED_VALUES_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> properties=findValues(specs,DISALLOWED_VALUES_CONSTRAINT_PID);
    return properties.stream().collect(Collectors.toSet());
  }
  return null;
}

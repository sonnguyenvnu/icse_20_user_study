@Override public Set<PropertyIdValue> mandatoryQualifiers(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,MANDATORY_QUALIFIERS_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> properties=findValues(specs,MANDATORY_QUALIFIERS_CONSTRAINT_PID);
    return properties.stream().filter(e -> e != null).map(e -> (PropertyIdValue)e).collect(Collectors.toSet());
  }
  return null;
}

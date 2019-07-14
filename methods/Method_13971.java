@Override public Set<PropertyIdValue> allowedQualifiers(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,ALLOWED_QUALIFIERS_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> properties=findValues(specs,ALLOWED_QUALIFIERS_CONSTRAINT_PID);
    return properties.stream().filter(e -> e != null).map(e -> (PropertyIdValue)e).collect(Collectors.toSet());
  }
  return null;
}

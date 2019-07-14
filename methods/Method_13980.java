@Override public Set<ItemIdValue> allowedUnits(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,ALLOWED_UNITS_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> properties=findValues(specs,ALLOWED_UNITS_CONSTRAINT_PID);
    return properties.stream().map(e -> e == null ? null : (ItemIdValue)e).collect(Collectors.toSet());
  }
  return null;
}

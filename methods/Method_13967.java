@Override public PropertyIdValue getInversePid(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,INVERSE_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> inverses=findValues(specs,INVERSE_PROPERTY_PID);
    if (!inverses.isEmpty()) {
      return (PropertyIdValue)inverses.get(0);
    }
  }
  return null;
}

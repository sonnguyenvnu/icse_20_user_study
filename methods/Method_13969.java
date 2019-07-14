@Override public boolean allowedAsQualifier(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,SCOPE_CONSTRAINT_QID);
  if (specs != null) {
    ItemIdValue target=Datamodel.makeWikidataItemIdValue(SCOPE_CONSTRAINT_QUALIFIER_QID);
    return findValues(specs,SCOPE_CONSTRAINT_PID).contains(target);
  }
  return true;
}

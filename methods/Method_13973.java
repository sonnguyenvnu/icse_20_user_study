@Override public boolean hasSingleValue(PropertyIdValue pid){
  return getSingleConstraint(pid,SINGLE_VALUE_CONSTRAINT_QID) != null;
}

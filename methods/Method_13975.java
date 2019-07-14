@Override public boolean hasDistinctValues(PropertyIdValue pid){
  return getSingleConstraint(pid,DISTINCT_VALUES_CONSTRAINT_QID) != null;
}

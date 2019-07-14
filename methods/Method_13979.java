@Override public boolean integerValued(PropertyIdValue pid){
  return getSingleConstraint(pid,INTEGER_VALUED_CONSTRAINT_QID) != null;
}

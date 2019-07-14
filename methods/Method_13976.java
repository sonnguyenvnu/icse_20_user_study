@Override public boolean isSymmetric(PropertyIdValue pid){
  return getSingleConstraint(pid,SYMMETRIC_CONSTRAINT_QID) != null;
}

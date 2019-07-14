@Override public boolean boundsAllowed(PropertyIdValue pid){
  return getSingleConstraint(pid,NO_BOUNDS_CONSTRAINT_QID) == null;
}

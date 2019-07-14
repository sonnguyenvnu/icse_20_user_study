@Override public boolean hasSingleBestValue(PropertyIdValue pid){
  return getSingleConstraint(pid,SINGLE_BEST_VALUE_CONSTRAINT_QID) != null;
}

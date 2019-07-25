@Override public SelectQueryImpl limit(final int limit){
  selectCore.limit(limit);
  return this;
}

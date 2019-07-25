@Override public SelectQueryImpl limit(final int limit,final long offSet){
  selectCore.limit(limit,offSet);
  return this;
}

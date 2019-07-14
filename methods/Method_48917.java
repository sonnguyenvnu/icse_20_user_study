@Override public MultiKeySliceQuery updateLimit(int newLimit){
  MultiKeySliceQuery newQuery=new MultiKeySliceQuery(queries);
  newQuery.setLimit(newLimit);
  return newQuery;
}

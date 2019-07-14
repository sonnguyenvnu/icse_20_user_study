@Override public void observeWith(QueryProfiler profiler){
  profiler.setAnnotation(QueryProfiler.CONDITION_ANNOTATION,condition);
  profiler.setAnnotation(QueryProfiler.ORDERS_ANNOTATION,orders);
  if (hasLimit())   profiler.setAnnotation(QueryProfiler.LIMIT_ANNOTATION,getLimit());
  queries.forEach(bqh -> bqh.observeWith(profiler));
}

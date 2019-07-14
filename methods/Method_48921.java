static QueryProfiler startProfile(QueryProfiler profiler,Subquery query){
  final QueryProfiler sub=profiler.addNested("backend-query");
  sub.setAnnotation(QUERY_ANNOTATION,query);
  if (query.hasLimit())   sub.setAnnotation(LIMIT_ANNOTATION,query.getLimit());
  sub.startTimer();
  return sub;
}

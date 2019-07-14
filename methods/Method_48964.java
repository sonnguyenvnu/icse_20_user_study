protected BaseVertexCentricQuery constructQuery(RelationCategory returnType){
  QueryProfiler optProfiler=profiler.addNested(QueryProfiler.OPTIMIZATION);
  optProfiler.startTimer();
  BaseVertexCentricQuery query=constructQueryWithoutProfile(returnType);
  optProfiler.stopTimer();
  query.observeWith(profiler);
  return query;
}

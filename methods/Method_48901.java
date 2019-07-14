public GraphCentricQuery constructQuery(final ElementCategory resultType){
  final QueryProfiler optProfiler=profiler.addNested(QueryProfiler.OPTIMIZATION);
  optProfiler.startTimer();
  if (this.globalConstraints.isEmpty()) {
    this.globalConstraints.add(this.constraints);
  }
  final GraphCentricQuery query=constructQueryWithoutProfile(resultType);
  optProfiler.stopTimer();
  query.observeWith(profiler);
  return query;
}

public static Collector<FullQuery,FullQuery> collect(final QueryTrace.Identifier what){
  final QueryTrace.NamedWatch w=QueryTrace.watch(what);
  return results -> {
    final ImmutableList.Builder<QueryTrace> traces=ImmutableList.builder();
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final ImmutableList.Builder<ResultGroup> groups=ImmutableList.builder();
    Statistics statistics=Statistics.empty();
    final ImmutableSet.Builder<ResultLimit> limits=ImmutableSet.builder();
    for (    final FullQuery r : results) {
      traces.add(r.trace());
      errors.addAll(r.errors());
      groups.addAll(r.groups());
      statistics=statistics.merge(r.statistics());
      limits.addAll(r.limits().getLimits());
    }
    return FullQuery.create(w.end(traces.build()),errors.build(),groups.build(),statistics,new ResultLimits(limits.build()),Optional.empty());
  }
;
}

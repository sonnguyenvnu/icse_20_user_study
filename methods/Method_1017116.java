@POST @Path("batch") public void metrics(@Suspended final AsyncResponse response,@QueryParam("backend") String group,@Context final HttpServletRequest servletReq,final QueryBatch query){
  final HttpContext httpContext=CoreHttpContextFactory.create(servletReq);
  final QueryManager.Group g=this.query.useOptionalGroup(Optional.ofNullable(group));
  final List<AsyncFuture<Triple<String,QueryContext,QueryResult>>> futures=new ArrayList<>();
  final Span currentSpan=tracer.getCurrentSpan();
  query.getQueries().ifPresent(queries -> {
    for (    final Map.Entry<String,QueryMetrics> e : queries.entrySet()) {
      final Span span=tracer.spanBuilderWithExplicitParent("batch.query",currentSpan).startSpan();
      final String queryKey=e.getKey();
      final QueryMetrics qm=e.getValue();
      final Query q=qm.toQueryBuilder(this.query::newQueryFromString).rangeIfAbsent(query.getRange()).build();
      final QueryContext queryContext=QueryContext.create(qm.clientContext(),httpContext);
      queryLogger.logHttpQueryJson(queryContext,qm);
      futures.add(g.query(q,queryContext).directTransform(r -> Triple.of(queryKey,queryContext,r)).onFinished(span::end));
    }
  }
);
  final AsyncFuture<QueryBatchResponse> future=async.collect(futures).directTransform(entries -> {
    final ImmutableMap.Builder<String,QueryMetricsResponse> results=ImmutableMap.builder();
    for (    final Triple<String,QueryContext,QueryResult> e : entries) {
      final String queryKey=e.getLeft();
      final QueryContext queryContext=e.getMiddle();
      final QueryResult r=e.getRight();
      final QueryMetricsResponse qmr=new QueryMetricsResponse(queryContext.queryId(),r.getRange(),r.getGroups(),r.getErrors(),r.getTrace(),r.getLimits(),Optional.of(r.getPreAggregationSampleSize()),r.getCache());
      queryLogger.logFinalResponse(queryContext,qmr);
      results.put(queryKey,qmr);
    }
    return new QueryBatchResponse(results.build());
  }
);
  response.setTimeout(300,TimeUnit.SECONDS);
  httpAsync.bind(response,future);
}

@Override public AsyncFuture<WriteMetric> write(final WriteMetric.Request request,final Span parentSpan){
  return connection.doto(c -> {
    final Series series=request.getSeries();
    final List<AsyncFuture<WriteMetric>> results=new ArrayList<>();
    final BigtableDataClient client=c.dataClient();
    final MetricCollection g=request.getData();
    results.add(writeTyped(series,client,g,parentSpan));
    return async.collect(results,WriteMetric.reduce());
  }
);
}

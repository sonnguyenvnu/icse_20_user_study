@Deprecated public static Collector<FetchData,FetchData> collect(final QueryTrace.Identifier what){
  final Collector<Result,Result> resultCollector=collectResult(what);
  return fetchDataCollection -> {
    final ImmutableList.Builder<Long> times=ImmutableList.builder();
    final Map<MetricType,ImmutableList.Builder<Metric>> fetchGroups=new HashMap<>();
    final ImmutableList.Builder<Result> results=ImmutableList.builder();
    for (    final FetchData fetch : fetchDataCollection) {
      times.addAll(fetch.times);
      results.add(fetch.result);
      for (      final MetricCollection g : fetch.groups) {
        ImmutableList.Builder<Metric> data=fetchGroups.get(g.getType());
        if (data == null) {
          data=new ImmutableList.Builder<>();
          fetchGroups.put(g.getType(),data);
        }
        data.addAll(g.data());
      }
    }
    final List<MetricCollection> groups=fetchGroups.entrySet().stream().map((e) -> MetricCollection.build(e.getKey(),Ordering.from(Metric.comparator()).immutableSortedCopy(e.getValue().build()))).collect(Collectors.toList());
    return new FetchData(resultCollector.collect(results.build()),times.build(),groups);
  }
;
}

@Override public TunnelSocketStatsMetricOverview analyze(TunnelSocketStatsResult result){
  List<Pair<TunnelSocketStatsMetric,TunnelSocketStatsMetric>> metrics=Lists.newArrayList();
  new SafeLoop<TunnelSocketStatsAnalyzer>(analyzers.values()){
    @Override protected void doRun0(    TunnelSocketStatsAnalyzer analyzer){
      metrics.add(analyzer.analyze(result));
    }
  }
.run();
  List<TunnelSocketStatsMetric> frontendMetric=Lists.newArrayListWithCapacity(metrics.size());
  List<TunnelSocketStatsMetric> backendMetric=Lists.newArrayListWithCapacity(metrics.size());
  for (  Pair<TunnelSocketStatsMetric,TunnelSocketStatsMetric> frontAndBackendMetric : metrics) {
    frontendMetric.add(frontAndBackendMetric.getKey());
    backendMetric.add(frontAndBackendMetric.getValue());
  }
  return new TunnelSocketStatsMetricOverview(frontendMetric,backendMetric);
}
